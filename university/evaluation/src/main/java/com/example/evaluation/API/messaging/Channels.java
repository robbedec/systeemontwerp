package com.example.evaluation.API.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {
	static final String PLAGIARISM_DETECTED_EVENT = "plagiarism_detected_event";
	
	@Output(PLAGIARISM_DETECTED_EVENT)
	MessageChannel plagiarismDetectedEvent();
}
