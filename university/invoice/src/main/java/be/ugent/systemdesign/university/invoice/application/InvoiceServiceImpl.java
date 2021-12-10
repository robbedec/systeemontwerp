package be.ugent.systemdesign.university.invoice.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.university.invoice.domain.Invoice;
import be.ugent.systemdesign.university.invoice.domain.InvoiceRepository;

@Transactional
@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	InvoiceRepository invoiceRepo;
	
	@Override
	public Response createNewRegistrationInvoice(String studentNumber, String name, String firstName, String faculty) {
		try {
			Invoice invoice = new Invoice(studentNumber, name, firstName);
			invoice.calculateAmount(faculty);
			invoiceRepo.save(invoice);
		} catch(RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "id "+studentNumber);
		}
		return new Response(ResponseStatus.SUCCESS, "id "+studentNumber);
	}

}
