package be.ugent.timgeldof.notification.application;

public interface NotificationService {

	Response notifyStudentCurriculumChange(String studentId, String courseName, String changeType);

	Response notifyStudentCourseMaterialVisibility(String studentId, String courseName, String fileName);

	Response registerNewStudentInformation(String studentId, String studentEmail, String firstName, String lastName);
}
