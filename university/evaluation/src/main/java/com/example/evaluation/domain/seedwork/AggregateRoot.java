package com.example.evaluation.domain.seedwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {
	private final List<DomainEvent> domainEvents = new ArrayList<>();

	protected void addDomainEvent(DomainEvent event) {
		domainEvents.add(event);
	}

	public List<DomainEvent> getDomainEvents() {
		return Collections.unmodifiableList(domainEvents);
	}

	public void clearDomainEvents() {
		domainEvents.clear();
	}
}