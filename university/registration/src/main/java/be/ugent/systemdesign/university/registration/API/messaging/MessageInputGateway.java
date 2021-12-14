package be.ugent.systemdesign.university.registration.API.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.university.registration.application.command.AccountCreatedResponse;
import be.ugent.systemdesign.university.registration.application.command.CommandHandler;
import be.ugent.systemdesign.university.registration.application.event.EventHandler;
import be.ugent.systemdesign.university.registration.application.event.InvoicePaidEvent;
import be.ugent.systemdesign.university.registration.application.event.PaymentOverdueEvent;
import be.ugent.systemdesign.university.registration.application.event.PlagiarismViolationEvent;
import be.ugent.systemdesign.university.registration.application.event.ScoresPublishedEvent;

@Component
public class MessageInputGateway {
	
	private static final Logger log = LoggerFactory.getLogger(MessageInputGateway.class);

	@Autowired
	EventHandler eventHandler;
	
	@Autowired
	CommandHandler commandHandler;
	
	@Autowired
	Channels channels;
	
	//events
	@StreamListener(Channels.INVOICE_PAID_EVENT)
	public void consumeInvoicePaidEvent(InvoicePaidEvent event){
		log.info("Registration has been paid");
		eventHandler.handleInvoicePaid(event);
	}
	
	@StreamListener(Channels.PAYMENT_LATE_EVENT)
	public void consumePayementLateEvent(PaymentOverdueEvent event) {
		log.info("Registration hasn't been paid on time");
		eventHandler.handlePaymentToLate(event);
	}
	
	@StreamListener(Channels.PLAGIARISM_EVENT)
	public void consumePlagiarismEvent(PlagiarismViolationEvent event) {
		log.info("Plagiarism noted");
		eventHandler.handlePlagiarismViolation(event);
	}
	
	@StreamListener(Channels.SCORES_PUBLISHED_EVENT)
	public void consumeScoresPublishedEvent(ScoresPublishedEvent event) {
		log.info("scores published");
		eventHandler.handleScoresPublished(event);
	}
	
	//commands
	@StreamListener(Channels.ACCOUNT_CREATED_RESPONSE)
	public void consumeAccountCreatedResponse(AccountCreatedResponse response) {
		log.info("accountCreatedResponse consumed");
		commandHandler.handleAccountCreatedResponse(response);
	}
}
