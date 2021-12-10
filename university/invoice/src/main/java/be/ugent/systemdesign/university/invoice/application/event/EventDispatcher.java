package be.ugent.systemdesign.university.invoice.application.event;

import be.ugent.systemdesign.university.invoice.domain.InvoicePaidEvent;
import be.ugent.systemdesign.university.invoice.domain.PaymentOverdueEvent;

public interface EventDispatcher {	
	void publishInvoicePaidEvent(InvoicePaidEvent event);
	void publishPaymentOverdueEvent(PaymentOverdueEvent event);
}
