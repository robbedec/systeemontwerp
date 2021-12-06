package be.ugent.timgeldof.learning_platform.application.query;

import java.util.List;

public interface CourseQuery {
	List<CourseViewModel> getAvailableCourses(String studentId);
	CourseWithCourseAnnouncementsViewModel getCourseAnnouncements(String studentId, int courseId);
	CourseWithCourseMaterialViewModel getCourseMaterial(String studentId, int courseId);
}

