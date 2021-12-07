package be.ugent.systemdesign.university.curriculum.application;

import be.ugent.systemdesign.university.curriculum.application.event.FacultyCoursesChangedEvent;
import be.ugent.systemdesign.university.curriculum.domain.FacultyCourseChangeType;

public interface CurriculumService {

	Response changeCurriculum(String curriculumId, String studentId);
	Response acceptCurriculum(String curriculumId, String userId);
	Response rejectCurriculum(String curriculumId, String userId);
	
	Response markCurriculumAsProposed(String curriculumId);
	
	Response noteNewRegistration(String studentId);
	Response noteDisenrollment(String studentId);
	Response noteFacultyCoursesChanged(String facultyName, FacultyCourseChangeType changeType, String courseName, Integer courseCredits);
}
