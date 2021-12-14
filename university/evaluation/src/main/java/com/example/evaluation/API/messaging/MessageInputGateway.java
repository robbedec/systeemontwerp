package com.example.evaluation.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.example.evaluation.application.event.CurriculumChangedEvent;
import com.example.evaluation.application.event.EventHandler;
import com.example.evaluation.application.event.FacultyEvent;

@Component
public class MessageInputGateway {
	@Autowired
	EventHandler eventHandler;
	
	@StreamListener(Channels.FACULTY_EVENT)
	public void consumeFacultyEvent(FacultyEvent event) {
		eventHandler.handleFacultyEvent(event);
	}
	
	@StreamListener(Channels.CURRICULUM_CHANGED_EVENT)
	public void handleCurriculumChangedEvent(CurriculumChangedEvent event) {
		eventHandler.handleCurriculumChangedEvent(event);
	}
}
