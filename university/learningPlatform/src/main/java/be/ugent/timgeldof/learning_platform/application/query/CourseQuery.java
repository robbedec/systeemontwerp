package be.ugent.timgeldof.learning_platform.application.query;

import java.util.List;

import be.ugent.timgeldof.learning_platform.domain.course_access.StudentNotFoundException;

public interface CourseQuery
 {	
	List<CourseViewModel> getAllCourses();
	List<CourseViewModel> getAvailableCourses(Integer studentId) throws StudentNotFoundException;
	CourseWithCourseAnnouncementsViewModel getCourseAnnouncements(Integer studentId, Integer courseId) throws StudentNotFoundException;
	CourseWithCourseMaterialViewModel getCourseMaterial(Integer studentId, Integer courseId) throws StudentNotFoundException;
}

