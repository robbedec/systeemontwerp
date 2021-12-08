package com.example.evaluation.application.query;

import java.time.LocalDate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskSubmissionReadModel {
	public final String studentId;
	public final String taskId;
	public final String file;
	public final LocalDate dateSubmitted;
	public final int score;
}
