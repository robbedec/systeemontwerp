package be.ugent.systemdesign.university.registration.domain;

import java.time.LocalDate;
import java.util.Date;

public class RegistrationGenerator {

	public Registration generateRegistration(Registration r) {		
		return new Registration(new Date(), r.getEmail(), r.getName(), r.getFirstName(), r.getDateOfBirth(), r.getFaculty(), r.getDegree());
	}
}
