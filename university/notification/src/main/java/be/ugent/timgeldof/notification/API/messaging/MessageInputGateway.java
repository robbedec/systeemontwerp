package be.ugent.timgeldof.notification.API.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import be.ugent.timgeldof.notification.application.command.GetStudentInformationCommand;
import be.ugent.timgeldof.notification.application.command.GetStudentInformationResponse;
import be.ugent.timgeldof.notification.application.event.CourseMaterialVisibleEvent;
import be.ugent.timgeldof.notification.application.event.CurriculumChangedEvent;
import be.ugent.timgeldof.notification.application.event.EventHandler;
import be.ugent.timgeldof.notification.application.event.NewRegistrationEvent;

@Component
public class MessageInputGateway{

	private static final Logger log = LoggerFactory.getLogger(MessageInputGateway.class);
	
	@Autowired
	EventHandler eventHandler;
	

	@StreamListener(Channels.CURRICULUM_CHANGED_EVENT)
	public void consumeCurriculumChangedEvent(CurriculumChangedEvent event) {
		eventHandler.handleCurriculumChangedEvent(event);
	}
	
	@StreamListener(Channels.COURSE_MATERIAL_VISIBILITY_EVENT)
	public void consumeCourseMaterialVisibilityEvent(CourseMaterialVisibleEvent event) {
		eventHandler.handleCourseMaterialVisibilityEvent(event);
	}
	
	@StreamListener(Channels.NEW_REGISTRATION_EVENT)
	@SendTo(Channels.STUDENT_INFORMATION_REQUEST)
	public GetStudentInformationCommand consumeNewRegistrationEvent(NewRegistrationEvent event) {
		return new GetStudentInformationCommand(event.getStudentId());
	}
	
	@StreamListener(Channels.STUDENT_INFORMATION_RESPONSE)
	public void consumeStudentInformationResponse(GetStudentInformationResponse response) {
		eventHandler.handleStudentInformationResponse(response);
	}
	
}
