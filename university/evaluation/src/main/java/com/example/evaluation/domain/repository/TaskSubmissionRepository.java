package com.example.evaluation.domain.repository;

import java.util.List;

import com.example.evaluation.domain.model.TaskSubmission;

public interface TaskSubmissionRepository {
	TaskSubmission findById(String taskSubmissionId);
	TaskSubmission save(TaskSubmission taskSubmission);
	List<TaskSubmission> findByTaskId(String taskId);
	TaskSubmission findByTaskIdAndStudentId(String taskId, String studentId);
}
