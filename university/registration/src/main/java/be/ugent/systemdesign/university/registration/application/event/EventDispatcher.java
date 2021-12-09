package be.ugent.systemdesign.university.registration.application.event;

import be.ugent.systemdesign.university.registration.domain.RegistrationAcceptedEvent;

public interface EventDispatcher {
	void publishRegistrationAcceptedEvent(RegistrationAcceptedEvent event);
}
