package be.ugent.systemdesign.university.curriculum.API.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.university.curriculum.application.event.EventHandler;
import be.ugent.systemdesign.university.curriculum.application.event.FacultyCoursesChangedEvent;
import be.ugent.systemdesign.university.curriculum.application.event.NewRegistrationEvent;

@Component
public class MessageInputGateway {
	
private static final Logger log = LoggerFactory.getLogger(MessageInputGateway.class);
	
	@Autowired
	EventHandler eventHandler;
	
	@Autowired
	Channels channels;
	
	@StreamListener(Channels.FACULTY_EVENT)
	public void consumeFacultyCoursesChangedEvent(FacultyCoursesChangedEvent event) {
		log.info("FACULTY COURSES CHANGED: {} ({} SP with id {}) {} in {} (fac. {}) resp. teacher: {}", event.getCourseName(), event.getCourseCredits(), event.getCourseId(), event.getChangeType(), event.getDegreeName(), event.getFacultyName(), event.getTeacherId());
		eventHandler.handleFacultyCoursesChanged(event);
	}
	
	@StreamListener(Channels.NEW_REGISTRATION_EVENT)
	public void consumeNewRegistrationEvent(NewRegistrationEvent event) {
		log.info("NEW REGISTRATION: {} enrolled in {} (fac. {})", event.getStudentNumber(), event.getDegreeName(), event.getFacultyName());
		eventHandler.handleNewEnrollment(event);
	}
}
