package be.ugent.timgeldof.notification.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import be.ugent.timgeldof.notification.application.event.EventHandler;

@Component
public class EmailProvider implements CommunicationProvider{

	private static final Logger log = LoggerFactory.getLogger(EmailProvider.class);

	
	@Override
	public void send(String target, String content) {
		log.info("Sending email to {}", target);
		log.info("Email content: {}", content);
	}

}
