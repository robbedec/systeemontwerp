package com.example.evaluation.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.evaluation.infrastructure.data_model.TaskDataModel;

public interface TaskDataModelRepository extends JpaRepository<TaskDataModel, String>{
	List<TaskDataModel> findByCourseId(String courseId);
}
