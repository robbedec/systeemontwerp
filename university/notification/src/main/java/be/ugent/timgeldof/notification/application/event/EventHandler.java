package be.ugent.timgeldof.notification.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.timgeldof.notification.application.NotificationService;
import be.ugent.timgeldof.notification.application.Response;

@Service
public class EventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	NotificationService service;
	
	public void handleCurriculumChangedEvent(CurriculumChangedEvent event) {
		Response r = service.notifyStudentCurriculumChange(event.getCourseName(), event.getChangeType());
		log.info(r.getMessage());
	}

	public void handleCourseMaterialVisibilityEvent(CourseMaterialVisibleEvent event) {
		Response r = service.notifyStudentCourseMaterialVisibility(event.getCourseName(), event.getFileName());		
		log.info(r.getMessage());
	}

	public void handleNewRegistrationEvent(NewRegistrationEvent event) {
		Response r = service.registerNewRegistration(event.getAccountId(), event.getEmail(), event.getDegree());
		log.info(r.getMessage());
	}
}
