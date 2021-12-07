package com.example.evaluation.application.query;

import java.util.List;

public interface TaskQuery {
	List<TaskSubmissionReadModel> getTaskSubmissions(String taskId);
}
