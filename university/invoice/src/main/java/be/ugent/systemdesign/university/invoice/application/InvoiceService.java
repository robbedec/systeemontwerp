package be.ugent.systemdesign.university.invoice.application;

public interface InvoiceService {

	Response createNewRegistrationInvoice(String studentNumber, String name, String firstName, String faculty);
	Response payInvoices(String studentNumber);
	Response expireInvoices(String studentNumber);
}
