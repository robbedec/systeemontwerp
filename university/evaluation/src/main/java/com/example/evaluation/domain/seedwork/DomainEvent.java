package com.example.evaluation.domain.seedwork;

import java.time.LocalDateTime;

import lombok.Getter;

public abstract class DomainEvent {
	@Getter
	private final LocalDateTime createdTime;

	public DomainEvent() {
		this.createdTime = LocalDateTime.now();
	}
}
