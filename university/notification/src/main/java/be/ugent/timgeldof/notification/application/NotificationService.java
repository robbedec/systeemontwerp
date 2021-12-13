package be.ugent.timgeldof.notification.application;

public interface NotificationService {

	Response notifyStudentCurriculumChange(String courseName, String changeType);

	Response notifyStudentCourseMaterialVisibility(String courseName, String fileName);

	Response registerNewRegistration(String accountId, String email, String degree);
}
