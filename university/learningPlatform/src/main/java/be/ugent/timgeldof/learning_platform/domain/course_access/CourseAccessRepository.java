package be.ugent.timgeldof.learning_platform.domain.course_access;

import java.util.List;

import be.ugent.timgeldof.learning_platform.domain.course.Course;

public interface CourseAccessRepository {
	List<CourseAccess> findAll();
	CourseAccess findById(Integer id) throws StudentNotFoundException;
	void save(CourseAccess c);
	void removeCourse(Course c);
}
