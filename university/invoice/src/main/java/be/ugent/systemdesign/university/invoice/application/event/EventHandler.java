package be.ugent.systemdesign.university.invoice.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.university.invoice.application.InvoiceService;
import be.ugent.systemdesign.university.invoice.application.Response;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	InvoiceService invoiceService;
	
	public void handleNewRegistrationEvent(NewRegistrationEvent event) {		
		Response response = invoiceService.createNewRegistrationInvoice(event.getAccountId(), event.getName(), event.getFirstName(), event.getFaculty());
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}
}
