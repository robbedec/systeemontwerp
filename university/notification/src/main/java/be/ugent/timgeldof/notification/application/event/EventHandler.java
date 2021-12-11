package be.ugent.timgeldof.notification.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.timgeldof.notification.application.NotificationService;
import be.ugent.timgeldof.notification.application.Response;
import be.ugent.timgeldof.notification.application.command.GetStudentInformationResponse;

@Service
public class EventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	NotificationService service;
	
	public void handleCurriculumChangedEvent(CurriculumChangedEvent event) {
		Response r = service.notifyStudentCurriculumChange(event.getStudentId(), event.getCourseName(), event.getChangeType());
		log.info(r.getMessage());
	}

	public void handleCourseMaterialVisibilityEvent(CourseMaterialVisibleEvent event) {
		Response r = service.notifyStudentCourseMaterialVisibility(event.getAccountId(), event.getCourseName(), event.getFileName());		
		log.info(r.getMessage());
	}

	public void handleStudentInformationResponse(GetStudentInformationResponse response) {
		Response r = service.registerNewStudentInformation(response.getStudentId(), response.getStudentEmail(), response.getFirstName(), response.getLastName());
		log.info(r.getMessage());
	}
}
