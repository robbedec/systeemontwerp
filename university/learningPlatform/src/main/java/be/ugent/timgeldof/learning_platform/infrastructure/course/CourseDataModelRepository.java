package be.ugent.timgeldof.learning_platform.infrastructure.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ugent.timgeldof.learning_platform.domain.course.Course;

public interface CourseDataModelRepository extends JpaRepository<CourseDataModel, Integer> {
	List<CourseDataModel> findAll();
	Optional<CourseDataModel> findByName(String name);
}
