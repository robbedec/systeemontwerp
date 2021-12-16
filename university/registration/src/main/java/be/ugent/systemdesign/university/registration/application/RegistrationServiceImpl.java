package be.ugent.systemdesign.university.registration.application;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.university.registration.application.command.CommandDispatcher;
import be.ugent.systemdesign.university.registration.application.command.CreateAccountCommand;
import be.ugent.systemdesign.university.registration.domain.InvalidRegistrationException;
import be.ugent.systemdesign.university.registration.domain.Registration;
import be.ugent.systemdesign.university.registration.domain.RegistrationRepository;

@Transactional
@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	RegistrationRepository registrationRepo;
	
	@Autowired
	CommandDispatcher commandDispatcher;

	@Override
	public Response addRegistration(String email, String name, String firstName, String dateOfBirth, String socialSecurityNumber, String faculty, String degree) {
		Registration r;		
		int id;
		try {
			r = new Registration(new Date(), email, name, firstName, LocalDate.parse(dateOfBirth), socialSecurityNumber, faculty, degree);			
			id = registrationRepo.save(r);
		} catch(InvalidRegistrationException ex) {
			return new Response(ResponseStatus.FAIL, "The registration was invalid");
		} catch(RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Registration could not be registered"+" - "+ex.getMessage());
		}		
		return new Response(ResponseStatus.SUCCESS, "id:"+id);
	}

	@Override
	public Response acceptRegistration(String registrationId) {
		Registration r;
		try {
			r = registrationRepo.findOne(Integer.parseInt(registrationId));
			if(r.getAccountId() == null){
				commandDispatcher.sendCreateAccountCommand(new CreateAccountCommand(r.getEmail(), r.getName(), r.getFirstName(), r.getDateOfBirth()));
				return new Response(ResponseStatus.SUCCESS, "Account is being created");
			} else {
			r.accept();
				registrationRepo.save(r);
			}
		} catch(RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Registration could not be accepted");
		}
		return new Response(ResponseStatus.SUCCESS, "id:"+registrationId);
	}

	@Override
	public Response rejectRegistration(String registrationId) {
		Registration r;
		try {
			r = registrationRepo.findOne(Integer.parseInt(registrationId));
			r.reject();
			registrationRepo.save(r);
		} catch(RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Failed to reject the registration");
		}
		return new Response(ResponseStatus.SUCCESS, "id:"+registrationId);
	}

	@Override
	public Response removeRegistration(String registrationId) {
		try {
			registrationRepo.removeRegistration(Integer.parseInt(registrationId));
		} catch(RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Failed to remove the registration");
		}
		return new Response(ResponseStatus.SUCCESS, "id:"+registrationId);
	}

	@Override
	public Response noteLatePayment(String accountId) {
		Registration r; 
		try {
			r = registrationRepo.getActiveRegistration(accountId);
			r.noteLatePayment();
			registrationRepo.save(r);
		} catch (RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Failed to note the late payment");
		}
		return new Response(ResponseStatus.SUCCESS, "id:"+r.getRegistrationId());
	}

	@Override
	public Response noteNewViolation(String accountId) {
		Registration r; 
		try {
			r = registrationRepo.getActiveRegistration(accountId);
			r.noteNewViolation();
			registrationRepo.save(r);
		} catch (RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Failed to note the new violation");
		}
		return new Response(ResponseStatus.SUCCESS, "id:"+r.getRegistrationId());
	}

	@Override
	public Response notePaidRegistration(String accountId) {
		Registration r; 
		try {
			r = registrationRepo.getActiveRegistration(accountId);
			r.notePayment();
			registrationRepo.save(r);
		} catch (RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Failed to note the payment");
		}
		return new Response(ResponseStatus.SUCCESS, "id:"+r.getRegistrationId());
	}
	
}
