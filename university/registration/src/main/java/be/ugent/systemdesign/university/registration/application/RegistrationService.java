package be.ugent.systemdesign.university.registration.application;

import java.util.Date;

public interface RegistrationService {
	
	Response addRegistration(String email, String name, String firstName, String dateOfBirth, String faculty, String degree);
	Response acceptRegistration(String registrationId);
	Response rejectRegistration(String registrationId);	
	Response removeRegistration(String registrationId);
	Response noteLatePayment(String accountId);
	Response noteNewViolation(String accountId);
	Response notePaidRegistration(String accountId);
}
