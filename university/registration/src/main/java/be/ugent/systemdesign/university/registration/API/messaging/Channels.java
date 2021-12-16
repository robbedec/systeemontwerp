package be.ugent.systemdesign.university.registration.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String NEW_REGISTRATION_EVENT = "new_registration_event";
	static final String SCORES_PUBLISHED_EVENT = "scores_published_event";
	static final String INVOICE_PAID_EVENT = "invoice_paid_event";
	static final String PAYMENT_LATE_EVENT = "payment_late_event";
	static final String PLAGIARISM_DETECTED_EVENT = "plagiarism_event";
	static final String CREATE_ACCOUNT_REQUEST = "create_account_request";
	static final String ACCOUNT_CREATED_RESPONSE = "account_created_response";
	
	//events
	@Output(NEW_REGISTRATION_EVENT)
	MessageChannel newRegistrationEvent();
	
	@Input(SCORES_PUBLISHED_EVENT)
	SubscribableChannel scoresPublishedEvent();
	
	@Input(INVOICE_PAID_EVENT)
	SubscribableChannel invoicePaidEvent();
	
	@Input(PAYMENT_LATE_EVENT)
	SubscribableChannel paymentLateEvent();
	
	@Input(PLAGIARISM_DETECTED_EVENT)
	SubscribableChannel plagiarismEvent();
	
	@Output(CREATE_ACCOUNT_REQUEST)
	MessageChannel createAccountCommand();
	
	//commands
	@Input(ACCOUNT_CREATED_RESPONSE)
	SubscribableChannel accountCreatedResponse();
}
