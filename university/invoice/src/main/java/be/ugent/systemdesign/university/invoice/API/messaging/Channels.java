package be.ugent.systemdesign.university.invoice.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String NEW_REGISTRATION_EVENT = "new_registration_event";
	static final String INVOICE_PAID_EVENT = "invoice_paid_event";
	static final String PAYMENT_OVERDUE_EVENT = "payment_overdue_event";
	
	@Output(INVOICE_PAID_EVENT)
	MessageChannel invoicePaidEvent();
	
	@Output(PAYMENT_OVERDUE_EVENT)
	MessageChannel paymentOverdueEvent();
	
	@Input(NEW_REGISTRATION_EVENT)
	SubscribableChannel newRegistrationEvent();
}
