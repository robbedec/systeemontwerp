package com.example.evaluation.API.rest.view_model;

import com.example.evaluation.application.query.read_model.TaskReadModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskViewModel {
	public final String taskId;
	public final String courseId;
	public final String description;
	public final String dueDate;
	public final String weight;
	
	public TaskViewModel(TaskReadModel taskRM) {
		taskId = taskRM.taskId;
		courseId = taskRM.courseId;
		description = taskRM.description;
		dueDate = taskRM.dueDate.toString();
		weight = taskRM.weight * 100 + "%";
	}
}
