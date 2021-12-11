package be.ugent.timgeldof.notification.application;

public interface NotificationService {

	Response notifyStudentCurriculumChange(Integer studentId, String courseName, String changeType);

	Response notifyStudentCourseMaterialVisibility(Integer studentId, String courseName, String fileName);

	Response registerNewRegistration(Integer accountId, String email, String degree);
}
