package com.example.evaluation.application.service;

import java.time.LocalDateTime;

public interface TaskService {
	Response createTask(String courseId, String description, LocalDateTime dueDate, double date);

	Response submitTask(String taskId, String studentId, String file);

	Response checkPlagiarism(String taskId);

	Response assignScore(String taskSubmissionId, int score);
}