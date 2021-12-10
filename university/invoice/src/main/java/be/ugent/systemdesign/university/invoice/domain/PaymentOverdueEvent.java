package be.ugent.systemdesign.university.invoice.domain;

import be.ugent.systemdesign.university.invoice.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class PaymentOverdueEvent extends DomainEvent {
	private String invoiceId;
	private String studentNumber;
	private String dueDate;
	private String amount;
	
	public PaymentOverdueEvent(String invoiceId, String studentNumber, String dueDate, String amount) {
		super();
		this.invoiceId = invoiceId;
		this.studentNumber = studentNumber;
		this.dueDate = dueDate;
		this.amount = amount;
	}
}
