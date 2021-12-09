package be.ugent.systemdesign.university.registration.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.university.registration.application.event.EventDispatcher;
import be.ugent.systemdesign.university.registration.domain.RegistrationAcceptedEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {
	
	@Gateway(requestChannel = Channels.NEW_REGISTRATION_EVENT)
	void publishRegistrationAcceptedEvent(RegistrationAcceptedEvent event);

}
