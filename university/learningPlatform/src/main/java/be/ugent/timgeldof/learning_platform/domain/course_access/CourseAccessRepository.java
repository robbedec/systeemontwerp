package be.ugent.timgeldof.learning_platform.domain.course_access;

import java.util.List;

public interface CourseAccessRepository {
	List<CourseAccess> findAll();
	CourseAccess findById(String id);
	void save(CourseAccess c);
}
