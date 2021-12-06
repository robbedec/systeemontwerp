package be.ugent.timgeldof.learning_platform.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ugent.timgeldof.learning_platform.application.command.CommandHandler;

@Component
public class MessagingInputGateway {

	@Autowired
	CommandHandler commandHandler;
	
	//@StreamListener(Channels.REGISTER_INPATIENT_INTAKE_REQUEST)
	//public void receiveRegisterInpatientIntakeCommand(RegisterInpatientIntakeCommand command) {
	//	commandHandler.registerInpatientIntake(command);
	//}
	
}
