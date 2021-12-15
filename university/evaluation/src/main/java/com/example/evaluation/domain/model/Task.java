package com.example.evaluation.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Task {

	private String taskId;
	private String courseId;
	private String description;
	private LocalDateTime dueDate;
	private double weight;

	public boolean dueDatePassed() {
		return LocalDateTime.now().isAfter(dueDate);
	}

}
