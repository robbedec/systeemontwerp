package be.ugent.timgeldof.learning_platform.application;

public interface LearningPlatformService {
	Response publishCourseMaterial(Integer courseId, byte[] file, String fileName);
	Response changeCourseMaterialVisibility(Integer courseId);
	Response addCourseAnnouncements(int courseId, String message);
	Response addCourse(String courseName, Integer courseCredits, Integer teacherId);
	Response removeCourse(String courseName, Integer courseCredits);
	Response registerInvoicePaid(Integer studentId);
	Response registerPlagiarism(Integer studentId, String changeType);
	Response changeCurriculum(String changeType, Integer studentId, String courseCredits, String courseName);
}
