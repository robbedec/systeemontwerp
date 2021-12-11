package be.ugent.systemdesign.university.invoice.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.university.invoice.application.event.EventHandler;
import be.ugent.systemdesign.university.invoice.application.event.NewRegistrationEvent;

@Component
public class MessageInputGateway {

	@Autowired
	EventHandler eventHandler;
	
	@StreamListener(Channels.NEW_REGISTRATION_EVENT)
	public void consumeNewRegistrationEvent(NewRegistrationEvent event) {
		eventHandler.handleNewRegistrationEvent(event);
	}
}
