package be.ugent.systemdesign.university.invoice.application.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.university.invoice.domain.InvoicePaidEvent;
import be.ugent.systemdesign.university.invoice.domain.PaymentOverdueEvent;

@Service
public class InvoiceEventListener {

	@Autowired
	EventDispatcher eventDispatcher;
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleInvoicePaidEventAsync(InvoicePaidEvent event) {
		eventDispatcher.publishInvoicePaidEvent(event);
	}
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handlePaymentOverdueEventAsync(PaymentOverdueEvent event) {
		eventDispatcher.publishPaymentOverdueEvent(event);
	}
}
