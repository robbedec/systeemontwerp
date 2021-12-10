package com.example.evaluation.API.rest.view_model;

import com.example.evaluation.application.query.TaskSubmissionReadModel;

public class TaskSubmissionViewModel {
	public final String studentId;
	public final String taskId;
	public final String file;
	public final String dateSubmitted;
	public final String score;

	public TaskSubmissionViewModel(TaskSubmissionReadModel taskSubmissionRM) {
		studentId = taskSubmissionRM.studentId;
		taskId = taskSubmissionRM.taskId;
		file = taskSubmissionRM.file;
		dateSubmitted = taskSubmissionRM.dateSubmitted.toString();
		score = taskSubmissionRM.score == -1 ? "-/20" : taskSubmissionRM.score + "/20";
	}
}
