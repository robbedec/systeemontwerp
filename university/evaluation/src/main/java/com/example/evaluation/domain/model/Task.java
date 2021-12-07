package com.example.evaluation.domain.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Task{
	private String taskId;
	private String courseId;
	private String description;
	private LocalDate dueDate;
}
