package com.example.evaluation.domain.model;

import java.time.LocalDateTime;

import com.example.evaluation.domain.event.PlagiarismDetectedDomainEvent;
import com.example.evaluation.domain.exception.InvalidScoreException;
import com.example.evaluation.domain.seedwork.AggregateRoot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskSubmission extends AggregateRoot {

	private String submissionId;
	private String taskId;
	private String studentId;
	private String file;
	private LocalDateTime dateSubmitted;
	private int score;

	public TaskSubmission(String taskId, String studentId) {
		this.taskId = taskId;
		this.studentId = studentId;
		this.score = -1;
	}

	public void assignScore(int score) {
		if (score < 0 || score > 20)
			throw new InvalidScoreException();
		this.score = score;
	}

	public boolean scoreIsAssigned() {
		return score != -1;
	}

	public void plagiarismDetected() {
		score = 0;
		addDomainEvent(new PlagiarismDetectedDomainEvent(taskId, studentId));
	}

	public boolean passed() {
		return score >= 10;
	}

}
