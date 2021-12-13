package be.ugent.timgeldof.learning_platform.application;

public interface LearningPlatformService {
	Response publishCourseMaterial(Integer courseId, byte[] file, String fileName);
	Response changeCourseMaterialVisibility(Integer courseId);
	Response addCourseAnnouncements(int courseId, String message);
	Response addCourse(Integer courseId, String courseName, Integer courseCredits, Integer teacherId);
	Response removeCourse(Integer courseId);
	Response registerInvoicePaid(Integer studentId);
	Response registerPlagiarism(Integer studentId);
	Response changeCurriculum(String changeType, Integer studentId, Integer courseId);
}
