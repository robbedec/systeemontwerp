package be.ugent.timgeldof.learning_platform.application.query;

import java.util.List;

public interface CourseQuery
 {	
	List<CourseViewModel> getAllCourses();
	List<CourseViewModel> getAvailableCourses(Integer studentId);
	CourseWithCourseAnnouncementsViewModel getCourseAnnouncements(Integer studentId, Integer courseId);
	CourseWithCourseMaterialViewModel getCourseMaterial(Integer studentId, Integer courseId);
}

