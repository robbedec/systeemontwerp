package com.example.evaluation.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskSubmissionDataModelRepository extends JpaRepository<TaskSubmissionDataModel, String>{
	List<TaskSubmissionDataModel> findByTaskId(String taskId);
	Optional<TaskSubmissionDataModel> findByTaskIdAndStudentId(String taskId, String studentId);
}
