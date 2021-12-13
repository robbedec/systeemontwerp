package be.ugent.timgeldof.learning_platform.application;

public interface LearningPlatformService {
	Response publishCourseMaterial(String courseId, byte[] file, String fileName);
	Response changeCourseMaterialVisibility(String courseId);
	Response addCourseAnnouncements(String courseId, String message);
	Response addCourse(String courseId, String courseName, Integer courseCredits, String teacherId);
	Response removeCourse(String courseId);
	Response registerInvoicePaid(String studentId);
	Response registerPlagiarism(String studentId);
	Response changeCurriculum(String changeType, String studentId, String courseId);
}
