package be.ugent.timgeldof.learning_platform.application.query;

import java.util.List;

import be.ugent.timgeldof.learning_platform.domain.course_access.StudentNotFoundException;

public interface CourseQuery
 {	
	List<CourseViewModel> getTeacherCourses(String teacherId);
	List<CourseViewModel> getAvailableCourses(String studentId) throws StudentNotFoundException;
	CourseWithCourseAnnouncementsViewModel getCourseAnnouncements(String studentId, String courseId) throws StudentNotFoundException;
	CourseWithCourseMaterialViewModel getCourseMaterial(String studentId, String courseId) throws StudentNotFoundException;
}

