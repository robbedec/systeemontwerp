package be.ugent.timgeldof.learning_platform.infrastructure.course_access;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ugent.timgeldof.learning_platform.infrastructure.course.CourseDataModel;

public interface CourseAccessJPARepository extends JpaRepository<CourseAccessDataModel, Integer> {
	List<CourseAccessDataModel> findAll();
}
