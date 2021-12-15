package com.example.evaluation.domain.event;

import com.example.evaluation.domain.seedwork.DomainEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlagiarismDetectedDomainEvent extends DomainEvent {

	private String taskId;
	private String studentId;

}
