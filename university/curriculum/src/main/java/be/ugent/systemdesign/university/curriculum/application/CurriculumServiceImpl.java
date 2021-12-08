package be.ugent.systemdesign.university.curriculum.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.university.curriculum.application.event.FacultyCoursesChangedEvent;
import be.ugent.systemdesign.university.curriculum.domain.Course;
import be.ugent.systemdesign.university.curriculum.domain.CourseDIFF;
import be.ugent.systemdesign.university.curriculum.domain.Curriculum;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumRepository;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumStatus;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumValidator;
import be.ugent.systemdesign.university.curriculum.domain.Faculty;
import be.ugent.systemdesign.university.curriculum.domain.FacultyCourseChangeType;
import be.ugent.systemdesign.university.curriculum.domain.FacultyCoursesRepository;
import be.ugent.systemdesign.university.curriculum.domain.exception.CurriculumInvalidException;
import be.ugent.systemdesign.university.curriculum.domain.exception.OnlyProposedCurriculumCanBeReviewedException;
import be.ugent.systemdesign.university.curriculum.infrastructure.CurriculumNotFoundException;
import be.ugent.systemdesign.university.curriculum.infrastructure.FacultyNotFoundException;

@Service
@Transactional
public class CurriculumServiceImpl implements CurriculumService {
	
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
			boolean isValid = CurriculumValidator.validateCurriculum(facultyAssignedToCurriculum.getAvailableCourses(), updatedCourses);
			
			if (isValid) {
				c.changeCurriculum(updatedCourses, userIsStudent(userId), changes);
			}
			
			curriculumRepo.save(c);
			
		} catch (CurriculumNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Could not find curriculum with id " + curriculumId);
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
		} catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "Could not find curriculum with id " + curriculumId);
		}
		
		return new Response(ResponseStatus.SUCCESS, "");
	}

	@Override
	public Response noteNewRegistration(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response noteDisenrollment(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Response noteFacultyCoursesChanged(String facultyName, FacultyCourseChangeType changeType,
			String courseName, Integer courseCredits) {
		
		Faculty faculty;
		
		try {
			faculty = facultyRepo.findByFacultyName(facultyName);
		} catch(FacultyNotFoundException e) {
			// Create a new faculty if it does not yet exist
			faculty = new Faculty(facultyName);
		}
		
		try {
			switch (changeType) {
				case ADDED:
					faculty.addCourse(courseName, courseCredits);
					break;
				case REMOVED:
					faculty.removeCourse(courseName, courseCredits);
					break;
			}
			
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, e.getMessage());
		}
		
		facultyRepo.save(faculty);
		
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
