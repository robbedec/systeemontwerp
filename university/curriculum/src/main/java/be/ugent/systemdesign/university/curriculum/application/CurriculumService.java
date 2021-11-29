package be.ugent.systemdesign.university.curriculum.application;

public interface CurriculumService {

	Response changeCurriculum(String curriculumId, String studentId);
	Response acceptCurriculum();
	Response rejectCurriculum();
	
	Response markCurriculumAsProposed(String curriculumId);
	
	Response noteNewRegistration(String studentId);
	Response noteDisenrollment(String studentId);
}
