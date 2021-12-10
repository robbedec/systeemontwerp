package be.ugent.systemdesign.university.registration.API.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.university.registration.application.RegistrationService;
import be.ugent.systemdesign.university.registration.application.Response;
import be.ugent.systemdesign.university.registration.application.ResponseStatus;
import be.ugent.systemdesign.university.registration.application.query.RegistrationQuery;

@RestController
@RequestMapping(path="api/registrations/")
@CrossOrigin(origins="*")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	RegistrationQuery registrationQuery;
	
	@GetMapping
	public List<RegistrationViewModel> getRegistrations(@RequestParam("status") String status){
		return registrationQuery.giveRegistrations(status)
				.stream()
				.map(el -> new RegistrationViewModel(el))
				.collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public RegistrationViewModel GetRegistration(@PathVariable("id") String registrationId) {
		return new RegistrationViewModel(registrationQuery.getRegistration(registrationId));
	}
	
	@PutMapping("{id}/accept")
	public ResponseEntity<String> acceptRegistration(@PathVariable("id") String registrationId) {
		Response response = registrationService.acceptRegistration(registrationId);
		return createResponseEntity(response.getStatus(), "Registration accepted", HttpStatus.OK, response.getMessage(),HttpStatus.CONFLICT);		
	}
	
	@PutMapping("{id}/reject")
	public ResponseEntity<String> rejectRegistration(@PathVariable("id") String registrationId) {
		Response response = registrationService.rejectRegistration(registrationId);
		return createResponseEntity(response.getStatus(), "Registration rejected", HttpStatus.OK, response.getMessage(),HttpStatus.CONFLICT);		
	}
	
	@PostMapping
	public ResponseEntity<String> createRegistration(@RequestBody RegistrationViewModel r){
		Response response = registrationService.addRegistration(
				r.getEmail(), 
				r.getName(), 
				r.getFirstName(),			
				r.getDateOfBirth(),
				r.getFaculty(),
				r.getDegree()
		);
		return createResponseEntity(response.getStatus(), "Registration confirmed", HttpStatus.OK, response.getMessage(),HttpStatus.CONFLICT);		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> removeRegistration(@PathVariable("id") String registrationId) {
		Response response = registrationService.removeRegistration(registrationId);
		return createResponseEntity(response.getStatus(), "Registration removed", HttpStatus.OK, response.getMessage(),HttpStatus.CONFLICT);
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}
}
