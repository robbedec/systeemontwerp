package com.example.evaluation.application.event;

import com.example.evaluation.domain.event.PlagiarismDetectedDomainEvent;

public interface EventDispatcher {

	void registerPlagiarismDetectedEvent(PlagiarismDetectedDomainEvent event);

}
