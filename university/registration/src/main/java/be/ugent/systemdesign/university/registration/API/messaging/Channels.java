package be.ugent.systemdesign.university.registration.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {
	static final String NEW_REGISTRATION_EVENT = "new_registration_event";
	static final String SCORES_PUBLISHED_EVENT = "scores_published_event";
	static final String INVOICE_PAID_EVENT = "invoice_paid_event";
	static final String PAYEMENT_LATE_EVENT = "payement_late_event";
	static final String PLAGIARISM_EVENT = "plagiarism_event";
	
	@Output(NEW_REGISTRATION_EVENT)
	MessageChannel newRegistrationEvent();
	
	@Input(SCORES_PUBLISHED_EVENT)
	MessageChannel scoresPublishedEvent();
	
	@Input(INVOICE_PAID_EVENT)
	MessageChannel invoicePaidEvent();
	
	@Input(PAYEMENT_LATE_EVENT)
	MessageChannel payementLateEvent();
	
	@Input(PLAGIARISM_EVENT)
	MessageChannel plagiarismEvent();
}
