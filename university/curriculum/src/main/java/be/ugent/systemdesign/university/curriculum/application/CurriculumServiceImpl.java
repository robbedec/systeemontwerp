package be.ugent.systemdesign.university.curriculum.application;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.university.curriculum.application.event.FacultyCoursesChangedEvent;
import be.ugent.systemdesign.university.curriculum.domain.Curriculum;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumRepository;
import be.ugent.systemdesign.university.curriculum.domain.Faculty;
import be.ugent.systemdesign.university.curriculum.domain.FacultyCourseChangeType;
import be.ugent.systemdesign.university.curriculum.domain.FacultyCoursesRepository;
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
	public Response changeCurriculum(String curriculumId, String userId) {
		
		try {
			Curriculum c = curriculumRepo.findByCurriculumId(curriculumId);
			c.changeCurriculum(new ArrayList<>(), userIsStudent(userId));
		} catch (CurriculumNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Could not find curriculum with id " + curriculumId);
		}
		
		return new Response(ResponseStatus.SUCCESS, "");
	}

	@Override
	public Response acceptCurriculum(String curriculumId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response rejectCurriculum(String curriculumId, String userId) {
		// TODO Auto-generated method stub
		return null;
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
