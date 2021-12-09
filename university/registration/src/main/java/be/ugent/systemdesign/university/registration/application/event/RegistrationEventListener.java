package be.ugent.systemdesign.university.registration.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.university.registration.domain.RegistrationAcceptedEvent;

@Service
public class RegistrationEventListener {

		private static final Logger log = LoggerFactory.getLogger(RegistrationEventListener.class);
		
		@Autowired
		EventDispatcher eventDispatcher;
		
		@Async
		@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
		public void handleRegistrationAcceptedEvent(RegistrationAcceptedEvent event) {
			log.info("handle RegistrationAcceptedEvent Async");
			eventDispatcher.publishRegistrationAcceptedEvent(event);
		}
		
		
		
}
