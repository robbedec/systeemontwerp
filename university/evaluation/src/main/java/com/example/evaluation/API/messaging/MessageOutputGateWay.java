package com.example.evaluation.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.example.evaluation.application.event.EventDispatcher;
import com.example.evaluation.domain.event.PlagiarismDetectedDomainEvent;

@MessagingGateway
public interface MessageOutputGateWay extends EventDispatcher {
	@Gateway(requestChannel = Channels.PLAGIARISM_DETECTED_EVENT)
	void publishPlagiarismDetectedEvent(PlagiarismDetectedDomainEvent event);
}
