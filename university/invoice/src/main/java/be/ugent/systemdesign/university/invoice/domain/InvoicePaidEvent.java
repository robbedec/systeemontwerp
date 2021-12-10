package be.ugent.systemdesign.university.invoice.domain;

import be.ugent.systemdesign.university.invoice.domain.seedwork.DomainEvent;

public class InvoicePaidEvent extends DomainEvent {
	private String invoiceId;
	private String studentNumber;
	private String amount;	
	
	public InvoicePaidEvent(String studentNumber, Double amount) {
		this.studentNumber = studentNumber;
		this.amount = amount.toString();		
	}
}
