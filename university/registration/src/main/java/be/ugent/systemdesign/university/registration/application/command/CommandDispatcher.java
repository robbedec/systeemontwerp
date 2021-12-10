package be.ugent.systemdesign.university.registration.application.command;

import org.springframework.stereotype.Service;

public interface CommandDispatcher {

	void sendCreateAccountCommand(CreateAccountCommand command);
}
