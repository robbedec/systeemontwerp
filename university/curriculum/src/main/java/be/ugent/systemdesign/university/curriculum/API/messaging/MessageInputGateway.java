package be.ugent.systemdesign.university.curriculum.API.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.university.curriculum.application.event.EventHandler;
import be.ugent.systemdesign.university.curriculum.application.event.FacultyCoursesChangedEvent;

@Component
public class MessageInputGateway {
	
private static final Logger log = LoggerFactory.getLogger(MessageInputGateway.class);
	
	@Autowired
	EventHandler eventHandler;
	
	@Autowired
	Channels channels;
	
	@StreamListener(Channels.FACULTY_EVENT)
	public void consumeFacultyCoursesChangedEvent(FacultyCoursesChangedEvent event) {
		log.info("{} ({} SP) was {} from {}: {}", event.getCourseName(), event.getCourseCredits().toString(), event.getChangeType(), event.getFacultyName(), event.getDegreeName());
		eventHandler.handleFacultyCoursesChanged(event);
	}
}
