package be.ugent.systemdesign.university.registration.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.university.registration.application.command.CommandDispatcher;
import be.ugent.systemdesign.university.registration.application.command.CreateAccountCommand;
import be.ugent.systemdesign.university.registration.application.event.EventDispatcher;
import be.ugent.systemdesign.university.registration.domain.RegistrationAcceptedEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher, CommandDispatcher {
	
	//events
	@Gateway(requestChannel = Channels.NEW_REGISTRATION_EVENT)
	void publishRegistrationAcceptedEvent(RegistrationAcceptedEvent event);
	
	//commands
	@Gateway(requestChannel = Channels.CREATE_ACCOUNT_COMMAND)
	void sendCreateAccountCommand(CreateAccountCommand command);

}
