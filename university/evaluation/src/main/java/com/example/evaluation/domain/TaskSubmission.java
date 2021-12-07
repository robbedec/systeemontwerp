package com.example.evaluation.domain;

import java.time.LocalDate;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskSubmission {
	@Id
	private String submissionId;
	private String taskId;
	private String studentId;
	private String file;
	private LocalDate dateSubmited;
	private int score;
	
	public boolean submitedBeforeDueDate(LocalDate dueDate) {
		if(dueDate == null)
			return true;
		return dateSubmited.isBefore(dueDate);
	}
	
	public boolean scoreIsAssigned() {
		return score != -1;
	}
}
