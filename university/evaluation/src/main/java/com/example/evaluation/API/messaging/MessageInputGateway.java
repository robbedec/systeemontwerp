package com.example.evaluation.API.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.example.evaluation.application.event.CurriculumChangedEvent;
import com.example.evaluation.application.event.EventHandler;
import com.example.evaluation.application.event.FacultyEvent;

@Component
public class MessageInputGateway {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	EventHandler eventHandler;

	@Autowired
	Channels channels;

	@StreamListener(Channels.FACULTY_EVENT)
	public void consumeFacultyEvent(FacultyEvent event) {
		log.info("FACULTY_EVENT");
		eventHandler.handleFacultyEvent(event);
	}

	@StreamListener(Channels.CURRICULUM_CHANGED_EVENT)
	public void handleCurriculumChangedEvent(CurriculumChangedEvent event) {
		log.info("CURRICULUM_CHANGED_EVENT");
		eventHandler.handleCurriculumChangedEvent(event);
	}

}
