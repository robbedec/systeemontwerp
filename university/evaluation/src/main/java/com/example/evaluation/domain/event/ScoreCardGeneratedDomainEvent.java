package com.example.evaluation.domain.event;

import com.example.evaluation.domain.seedwork.DomainEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScoreCardGeneratedDomainEvent extends DomainEvent {
	private String studentId;
	private String degreeId;
	private boolean passed;
}
