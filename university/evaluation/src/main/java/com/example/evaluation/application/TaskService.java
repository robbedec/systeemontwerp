package com.example.evaluation.application;

import java.time.LocalDate;

public interface TaskService {
	Response createTask(String courseId, String description, LocalDate dueDate);
	Response submitTask(String taskId, String studentId, String file);
	Response assignScore(String taskId, String studentId, int score);
}
