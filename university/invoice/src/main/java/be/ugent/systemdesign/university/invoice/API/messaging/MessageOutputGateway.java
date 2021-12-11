package be.ugent.systemdesign.university.invoice.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.university.invoice.application.event.EventDispatcher;
import be.ugent.systemdesign.university.invoice.domain.InvoicePaidEvent;
import be.ugent.systemdesign.university.invoice.domain.PaymentOverdueEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher{

	@Gateway(requestChannel = Channels.INVOICE_PAID_EVENT)
	void publishInvoicePaidEvent(InvoicePaidEvent event);
	
	@Gateway(requestChannel = Channels.PAYMENT_OVERDUE_EVENT)
	void publishPaymentOverdueEvent(PaymentOverdueEvent event);
}
