package be.ugent.systemdesign.university.registration.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.university.registration.application.RegistrationService;
import be.ugent.systemdesign.university.registration.application.Response;

@Service
public class EventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	RegistrationService registrationService;
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handlePassedDegree(PassedDegreeEvent event) {
		
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleInvoicePaid(InvoicePaidEvent event) {
		String accountId = event.getStudentNumber();
		Response response = registrationService.notePaidRegistration(accountId); 
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handlePaymentToLate(PaymentOverdueEvent event) {
		String accountId = event.getStudentNumber();
		Response response = registrationService.noteLatePayment(accountId);
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handlePlagiarismViolation(PlagiarismViolationEvent event) {
		String accountId = event.getStudentId();
		Response response = registrationService.noteNewViolation(accountId);
		log.info("-response status[{}] message[{}]", response.getStatus(), response.getMessage());
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleScoresPublished(ScoresPublishedEvent event) {
		
	}
}
