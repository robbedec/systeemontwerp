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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.university.registration.application.RegistrationService;
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
	public List<RegistrationViewModel> getRegistrations(@RequestParam("isAccepted") boolean isAccepted){
		return registrationQuery.giveRegistrations(isAccepted)
				.stream()
				.map(el -> new RegistrationViewModel(el))
				.collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public RegistrationViewModel GetRegistration(@PathVariable("id") String registrationId) {
		return null;
	}
	
	@PutMapping("{id}/accept")
	public ResponseEntity<String> acceptRegistration(@PathVariable("id") String registrationId) {
		//return createResponseEntity(response.status, "Intake registered", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
		return null;
	}
	
	@PutMapping("{id}/reject")
	public ResponseEntity<String> rejectRegistration(@PathVariable("id") String registrationId) {
		//return createResponseEntity(response.status, "Intake registered", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
		return null;
	}
	
	@PostMapping
	public ResponseEntity<String> createRegistration(){
		//return createResponseEntity(response.status, "Intake registered", HttpStatus.OK, response.message,HttpStatus.CONFLICT);
		return null;
	}
	
	@DeleteMapping
	public void removeRegistration() {
		
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}
}
