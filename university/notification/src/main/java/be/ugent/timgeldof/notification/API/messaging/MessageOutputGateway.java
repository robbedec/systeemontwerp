package be.ugent.timgeldof.notification.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.timgeldof.notification.application.command.CommandDispatcher;
import be.ugent.timgeldof.notification.application.command.GetStudentInformationCommand;

@MessagingGateway
public interface MessageOutputGateway extends CommandDispatcher {
	
	@Gateway(requestChannel = Channels.STUDENT_INFORMATION_REQUEST)
	void getStudentInformation(GetStudentInformationCommand command);
}