package com.example.evaluation.domain;

import java.util.List;

public interface TaskSubmissionRepository {
	TaskSubmission findById(String taskSubmissionId);
	void save(TaskSubmission taskSubmission);
	List<TaskSubmission> findByTaskId(String taskId);
	TaskSubmission findByTaskIdAndStudentId(String taskId, String studentId);
}
