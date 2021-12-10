package be.ugent.systemdesign.university.curriculum.application;

import java.util.List;

import be.ugent.systemdesign.university.curriculum.application.event.FacultyCoursesChangedEvent;
import be.ugent.systemdesign.university.curriculum.domain.Course;
import be.ugent.systemdesign.university.curriculum.domain.FacultyCourseChangeType;

public interface CurriculumService {

	Response changeCurriculum(String curriculumId, String studentId, List<Course> changedCourses);
	Response reviewCurriculumStatus(String curriculumId, String verdict, String userId);
	
	Response markCurriculumAsProposed(String curriculumId);
	
	Response noteNewRegistration(String studentId, String facultyName, String degreeName);
	Response noteDisenrollment(String studentId);
	Response noteFacultyCoursesChanged(String facultyName, String degreeName, FacultyCourseChangeType changeType, String courseName, Integer courseCredits);
}
