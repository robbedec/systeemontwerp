package com.example.evaluation.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDataModelRepository extends JpaRepository<TaskDataModel, String>{
	List<TaskDataModel> findByCourseId(String courseId);
}
