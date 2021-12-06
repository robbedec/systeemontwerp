package com.example.evaluation.domain;

import java.util.List;

public interface TaskRepository {
	Task findById(String taskId);
	void save(Task task);
	List<Task> findByCourseId(String courseId);
}
