package be.ugent.timgeldof.learning_platform.application;

public interface LearningPlatformService {
	Response publishCourseMaterial(Integer courseId, byte[] file, String fileName);
	Response changeCourseMaterialVisibility(Integer courseId);
	Response addCourseAnnouncements(int courseId, String message);
}