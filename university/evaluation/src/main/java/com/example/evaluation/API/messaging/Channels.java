package com.example.evaluation.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Component
public interface Channels {
	static final String PLAGIARISM_DETECTED_EVENT = "plagiarism_detected_event";
	static final String SCORE_CARD_GENERATED_EVENT = "score_card_generated_event";
	static final String FACULTY_EVENT = "faculty_event";
	static final String CURRICULUM_CHANGED_EVENT = "curriculum_changed_event";

	@Output(PLAGIARISM_DETECTED_EVENT)
	MessageChannel plagiarismDetectedEvent();
	
	@Output(SCORE_CARD_GENERATED_EVENT)
	MessageChannel scoreCardGeneratedEvent();
	
	@Input(FACULTY_EVENT)
	SubscribableChannel facultyEvent();
	
	@Input(CURRICULUM_CHANGED_EVENT)
	SubscribableChannel curriculumChangedEvent();
}
