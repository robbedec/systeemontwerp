package be.ugent.timgeldof.learning_platform.API.messaging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.timgeldof.learning_platform.application.command.CommandHandler;
import be.ugent.timgeldof.learning_platform.application.event.EventHandler;
import be.ugent.timgeldof.learning_platform.application.event.FacultyCoursesChangedEvent;

@Component
public class MessageInputGateway {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

	
	@Autowired
	EventHandler eventHandler;
	

	@StreamListener(Channels.FACULTY_EVENT)
	public void consumeFacultyCoursesChangedEvent(FacultyCoursesChangedEvent event) {
		log.info("Message input gateway received facultyCourseChangedEvent of type " + event.getChangeType());
		eventHandler.handleFacultyCoursesChanged(event);
	}
	
}
