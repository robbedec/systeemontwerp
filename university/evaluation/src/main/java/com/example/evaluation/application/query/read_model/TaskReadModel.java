package com.example.evaluation.application.query.read_model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskReadModel {

	public final String taskId;
	public final String courseId;
	public final String description;
	public final String courseName;
	public final LocalDateTime dueDate;
	public final double weight;

}
