package com.example.evaluation.application.query.read_model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskSubmissionReadModel {

	public final String studentId;
	public final String taskId;
	public final String file;
	public final LocalDateTime dateSubmitted;
	public final int score;

}
