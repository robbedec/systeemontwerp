package com.example.evaluation.domain.repository;

import java.util.List;

import com.example.evaluation.domain.model.Task;

public interface TaskRepository {
	Task findById(String taskId);
	Task save(Task task);
	List<Task> findByCourseId(String courseId);
}
