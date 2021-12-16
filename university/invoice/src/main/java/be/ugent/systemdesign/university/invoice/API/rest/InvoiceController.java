package be.ugent.systemdesign.university.invoice.API.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.university.invoice.application.InvoiceService;
import be.ugent.systemdesign.university.invoice.application.Response;
import be.ugent.systemdesign.university.invoice.application.ResponseStatus;

@RestController
@RequestMapping(path="api/invoice/")
@CrossOrigin(origins="*")
public class InvoiceController {
	
	@Autowired
	InvoiceService invoiceService;
	
	@PutMapping("{id}/pay")
	public ResponseEntity<String> payInvoice(@PathVariable("id") String studentNumber){
		Response response = invoiceService.payInvoices(studentNumber);
		return createResponseEntity(response.status, "studentNumber: "+studentNumber, HttpStatus.OK, response.message ,HttpStatus.CONFLICT);		
	}
	
	@PutMapping("{id}/expire")
	public ResponseEntity<String> expireInvoice(@PathVariable("id") String studentNumber){
		Response response = invoiceService.expireInvoices(studentNumber);
		return createResponseEntity(response.status, "studentNumber: "+studentNumber, HttpStatus.OK, response.message ,HttpStatus.CONFLICT);		
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}

}
