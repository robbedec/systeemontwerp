package be.ugent.systemdesign.university.registration.application;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.university.registration.domain.Registration;
import be.ugent.systemdesign.university.registration.domain.RegistrationRepository;

@Transactional
@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	RegistrationRepository registrationRepo;

	@Override
	public Response addRegistration(String email, String name, String firstName,
			LocalDate dateOfBirth, String course) {
		Registration r;
		try {
			r = new Registration(new Date(), email, name, firstName, dateOfBirth, course);
			registrationRepo.save(r);
		} catch(RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Registration could not be registered");
		}		
		return new Response(ResponseStatus.SUCCESS, "id:"+r.getRegistrationId());
	}

	@Override
	public Response acceptRegistration(String registrationId) {
		Registration r;
		try {
			r = registrationRepo.findOne(Integer.parseInt(registrationId));
			r.accept();
			registrationRepo.save(r);
		} catch(RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Registration could not be accepted");
		}
		return new Response(ResponseStatus.SUCCESS, "id:"+r.getRegistrationId());
	}

	@Override
	public Response rejectRegistration(String registrationId) {
		// TODO Auto-generated method stub
		Registration r;
		try {
			r = registrationRepo.findOne(Integer.parseInt(registrationId));
			r.reject();
			registrationRepo.save(r);
		} catch(RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Failed to reject the registration");
		}
		return new Response(ResponseStatus.SUCCESS, "id:"+r.getRegistrationId());
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
	
}
