package be.ugent.systemdesign.university.curriculum.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.university.curriculum.CurriculumApplication;
import be.ugent.systemdesign.university.curriculum.application.event.FacultyCoursesChangedEvent;
import be.ugent.systemdesign.university.curriculum.domain.Course;
import be.ugent.systemdesign.university.curriculum.domain.CourseDIFF;
import be.ugent.systemdesign.university.curriculum.domain.Curriculum;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumRepository;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumStatus;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumValidator;
import be.ugent.systemdesign.university.curriculum.domain.DegreeProgramme;
import be.ugent.systemdesign.university.curriculum.domain.Faculty;
import be.ugent.systemdesign.university.curriculum.domain.FacultyCourseChangeType;
import be.ugent.systemdesign.university.curriculum.domain.FacultyCoursesRepository;
import be.ugent.systemdesign.university.curriculum.domain.exception.CurriculumInvalidException;
import be.ugent.systemdesign.university.curriculum.domain.exception.CurriculumRemovedException;
import be.ugent.systemdesign.university.curriculum.domain.exception.DuplicateCourseException;
import be.ugent.systemdesign.university.curriculum.domain.exception.OnlyProposedCurriculumCanBeReviewedException;
import be.ugent.systemdesign.university.curriculum.domain.exception.OnlyProvisionOrRejectedCurriculumCanBeProposedException;
import be.ugent.systemdesign.university.curriculum.infrastructure.CurriculumNotFoundException;
import be.ugent.systemdesign.university.curriculum.infrastructure.FacultyNotFoundException;

@Service
@Transactional
public class CurriculumServiceImpl implements CurriculumService {
	
	private static final Logger log = LoggerFactory.getLogger(CurriculumServiceImpl.class);
	
	@Autowired
	CurriculumRepository curriculumRepo;
	
	@Autowired
	FacultyCoursesRepository facultyRepo;

	@Override
	public Response changeCurriculum(String curriculumId, String userId, List<Course> changedCourses) {
		
		try {
			Curriculum c = curriculumRepo.findByCurriculumId(curriculumId);
			
			// Changes made in the current courses are logged in a HashMap
			// This map is used to dispatch the curriculum changed event
			HashMap<Course, FacultyCourseChangeType> changes = new HashMap<>();
			List<Course> updatedCourses = CourseDIFF.createDIFF(c.getCourses(), changedCourses, changes);
			
			Faculty facultyAssignedToCurriculum = facultyRepo.findByFacultyName(c.getFacultyName());
			DegreeProgramme degreeProgrammeAssignedToCurriculum = facultyAssignedToCurriculum.getDegrees().stream().filter(x -> x.getDegreeName().equals(c.getDegreeName())).findAny().orElseThrow(FacultyNotFoundException::new);
			
			boolean isValid = CurriculumValidator.validateCurriculum(degreeProgrammeAssignedToCurriculum.getAvailableCourses(), updatedCourses);
			
			if (isValid) {
				c.changeCurriculum(updatedCourses, userIsStudent(userId), changes);
			}
			
			curriculumRepo.save(c);
			
		} catch (CurriculumNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Could not find curriculum with id " + curriculumId);
		} catch (CurriculumRemovedException e) {
			return new Response(ResponseStatus.FAIL, "Curriculum was previously removed");
		} catch(CurriculumInvalidException e) {
			return new Response(ResponseStatus.FAIL, "Invalid curriculum");
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Unknown exception occured: " + e.getMessage());
		}
		
		return new Response(ResponseStatus.SUCCESS, "Curriculum " + curriculumId + " succesfully changed");
	}
	
	@Override
	public Response reviewCurriculumStatus(String curriculumId, String verdict, String userId) {
		// Check if verdict is a valid curriculumstatus
		if (!verdict.equals(CurriculumStatus.ACCEPTED.name()) && !verdict.equals(CurriculumStatus.REJECTED.name())) {
			return new Response(ResponseStatus.FAIL, "Unknown review status: " + verdict);
		}
		
		try {
			Curriculum c = curriculumRepo.findByCurriculumId(curriculumId);
			c.reviewCurriculum(CurriculumStatus.valueOf(verdict), userIsStudent(userId));
			
			curriculumRepo.save(c);
			
		} catch(CurriculumNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Could not find curriculum with id " + curriculumId);
		} catch (CurriculumRemovedException e) {
			return new Response(ResponseStatus.FAIL, "Curriculum was previously removed");
		} catch (IllegalAccessException e) {
			return new Response(ResponseStatus.FAIL, "Curriculum can not be reviewed by a student");
		} catch (OnlyProposedCurriculumCanBeReviewedException e) {
			return new Response(ResponseStatus.FAIL, "Only proposed curriculi can be reviewed");
		}
		
		return new Response(ResponseStatus.SUCCESS, "Curriculum " + curriculumId + " received new status: " + verdict);
	}

	@Override
	public Response markCurriculumAsProposed(String curriculumId) {

		try {
			Curriculum c = curriculumRepo.findByCurriculumId(curriculumId);
			c.markCurriculumAsProposed();
			
			curriculumRepo.save(c);
		} catch (CurriculumNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Could not find curriculum with id " + curriculumId);
		} catch (CurriculumRemovedException e) {
			return new Response(ResponseStatus.FAIL, "Curriculum was previously removed");
		} catch (OnlyProvisionOrRejectedCurriculumCanBeProposedException e) {
			return new Response(ResponseStatus.FAIL, "Invalid operation");
		}
		
		return new Response(ResponseStatus.SUCCESS, "");
	}

	@Override
	public Response noteNewRegistration(String studentId, String facultyName, String degreeName) {
		Curriculum c;
		
		// Check if the student already has a curriculum in the given faculty / degree
		try {
			// Student already attended a previous year in the giver faculty / degree.
			c = curriculumRepo.findByStudentIdAndFacultyNameAndDegreeName(studentId, facultyName, degreeName);
			c.transferHistory();
		} catch (CurriculumNotFoundException e) {
			// New student
			c = new Curriculum(studentId, facultyName, degreeName);
		} catch (CurriculumRemovedException e) {
			return new Response(ResponseStatus.FAIL, "Curriculum was previously removed");
		} catch (UnsupportedOperationException e) {
			// There exist multiple curricula for a student in the same faculty and degree.
			// This should never happen if new registrations are handled in the correct manner. 
			
			return new Response(ResponseStatus.FAIL, "Internal system error: multiple curricula exist for " + studentId);
		}
		
		curriculumRepo.save(c);
		return new Response(ResponseStatus.SUCCESS, "");
	}

	@Override
	public Response noteDisenrollment(String studentId) {
		try {
			Curriculum c = curriculumRepo.findByStudentId(studentId);
			
			// We chose to soft delete a curriculum to preserve history
			c.setDeleted(true);
			curriculumRepo.save(c);
			
		} catch(CurriculumNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Could not find curriculum with id " + studentId);
		}
		
		return new Response(ResponseStatus.SUCCESS, "");
	}
	
	@Override
	public Response noteFacultyCoursesChanged(String facultyName, String degreeName, FacultyCourseChangeType changeType,
			Integer courseId, String courseName, Integer courseCredits) {
		
		Faculty faculty;
		DegreeProgramme degree;
		
		// After this try-catch block the faculty and degree should exist
		try {
			
			faculty = facultyRepo.findByFacultyName(facultyName);
			// Create new degree in the faculty if it does not yet exist
			if (!faculty.getDegrees().stream().anyMatch(x -> x.getDegreeName().equals(degreeName))) {
				faculty.addDegree(degreeName);
			}
		} catch(Exception e) {
			// Create a new faculty if it does not yet exist
			faculty = new Faculty(facultyName);
			faculty.addDegree(degreeName);
		}
		
		try {
			degree = faculty.getDegrees().stream().filter(x -> x.getDegreeName().equals(degreeName)).findAny().orElseThrow(Exception::new);
			
			switch (changeType) {
				case ADDED:
					degree.addCourse(courseId, courseName, courseCredits);
					break;
				case REMOVED:
					degree.removeCourse(courseId, courseName, courseCredits);
					break;
			}
			
			facultyRepo.save(faculty);
			
		} catch (DuplicateCourseException e) {
			return new Response(ResponseStatus.SUCCESS, "Course is already present");
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, e.getMessage());
		}
		
		return new Response(ResponseStatus.SUCCESS, "");
	}
	
	/*
	 * We can check if userId (the identifier of the user that made the change)
	 * is a student or not by checking if a curriculum exists for that user. If not,
	 * the user is a university administrator
	 */
	 private boolean userIsStudent(String userId) {
		try {
			Curriculum c = curriculumRepo.findByStudentId(userId);
			return true;
		} catch (CurriculumNotFoundException e) {
			return false;
		}
	 }
}
