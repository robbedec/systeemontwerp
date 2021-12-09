package be.ugent.systemdesign.university.registration.application;

import java.time.LocalDate;
import java.util.Date;

public interface RegistrationService {
	
	Response addRegistration(String email, String name, String firstName, LocalDate dateOfBirth, String course);
	Response acceptRegistration(String registrationId);
	Response rejectRegistration(String registrationId);	
	Response removeRegistration(String registrationId);
	
}
