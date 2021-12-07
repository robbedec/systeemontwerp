package com.example.evaluation.domain.model;

import java.time.LocalDate;

import javax.persistence.Id;

import com.example.evaluation.domain.exception.InvalidScoreException;

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
	private LocalDate dateSubmitted;
	private int score;
	
	public boolean submittedBeforeDueDate(LocalDate dueDate) {
		if(dueDate == null)
			return true;
		return dateSubmitted.isBefore(dueDate);
	}
	
	public void assignScore(int score) {
		if(score < 0 || score > 20)
			throw new InvalidScoreException();
		this.score = score;
	}
	
	public boolean scoreIsAssigned() {
		return score != -1;
	}
}
