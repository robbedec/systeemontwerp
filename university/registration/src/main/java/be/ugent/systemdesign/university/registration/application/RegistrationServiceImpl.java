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
	public Response register(Date registrationDate, String email, String name, String firstName,
			LocalDate dateOfBirth, String course) {
		Registration r;
		try {
			r = new Registration(registrationDate, email, name, firstName, dateOfBirth, course);
			registrationRepo.save(r);
		} catch(RuntimeException ex) {
			return new Response(ResponseStatus.FAIL, "Registration could not be registered");
		}
		
		return new Response(ResponseStatus.SUCCESS, "id:"+r.getRegistrationId());
	}
	
}
