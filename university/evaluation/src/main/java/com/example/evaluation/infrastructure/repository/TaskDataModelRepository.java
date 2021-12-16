package com.example.evaluation.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.evaluation.infrastructure.data_model.TaskDataModel;

public interface TaskDataModelRepository extends JpaRepository<TaskDataModel, String> {

	List<TaskDataModel> findByCourseId(String courseId);

	List<TaskDataModel> findByCourseIdIn(List<String> courseIds);

	@Query(value = "SELECT SUM(weight) FROM task_data_model WHERE course_id = ?1", nativeQuery = true)
	Optional<Double> findTotalWeight(String courseId);

}
