package be.ugent.systemdesign.university.registration.application;

import java.time.LocalDate;
import java.util.Date;

public interface RegistrationService {
	
	Response register(Date registrationDate, String email, String name, String firstName, LocalDate dateOfBirth, String course);
}
