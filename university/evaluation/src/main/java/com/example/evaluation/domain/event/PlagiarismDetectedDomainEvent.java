package com.example.evaluation.domain.event;

import com.example.evaluation.domain.seedwork.DomainEvent;

import lombok.Getter;

@Getter
public class PlagiarismDetectedDomainEvent extends DomainEvent{
	private String taskId;
	private String studentId;
	
	public PlagiarismDetectedDomainEvent(String taskId, String studentId) {
		this.taskId = taskId;
		this.studentId = studentId;
	}
}
