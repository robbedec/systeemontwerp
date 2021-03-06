package be.ugent.timgeldof.learning_platform.infrastructure.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ugent.timgeldof.learning_platform.domain.course.Course;

public interface CourseDataModelRepository extends JpaRepository<CourseDataModel, String> {
	List<CourseDataModel> findAll();
	Optional<CourseDataModel> findByNameAndCourseCredits(String name, Integer courseCredits);
}
