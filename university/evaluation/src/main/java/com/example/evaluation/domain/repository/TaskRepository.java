package com.example.evaluation.domain.repository;

import java.util.List;

import com.example.evaluation.domain.model.Task;
import com.example.evaluation.domain.model.TaskSubmission;

public interface TaskRepository {

	Task findById(String taskId);

	Task save(Task task);

	List<Task> findByCourseId(String courseId);

	TaskSubmission findSubmissionById(String taskSubmissionId);

	TaskSubmission save(TaskSubmission taskSubmission);

	List<TaskSubmission> findSubmissionsByTaskId(String taskId);

	TaskSubmission findSubmissionByTaskIdAndStudentId(String taskId, String studentId);

	double findTotalWeight(String courseId);

}
