package be.ugent.timgeldof.learning_platform.domain.course;

import java.util.List;

public interface CourseRepository {
	List<Course> findAll();
	Course findOne(Integer id);
	void save(Course c);
	void remove(Course c);
}
