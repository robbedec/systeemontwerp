package be.ugent.systemdesign.university.registration.domain;

import java.time.LocalDate;
import java.util.Date;

public class RegistrationGenerator {

	public static Registration generateRegistration(Registration r) {
		Registration newRegistration;
		if(r.isPaid() && !r.hasOpenViolations()) {
			 newRegistration = new Registration(new Date(), r.getEmail(), r.getName(), r.getFirstName(), r.getDateOfBirth(), r.getFaculty(), r.getDegree());
			newRegistration.setStatus(Status.GENERATED);			
		} else {
			throw new RuntimeException();
		}	
		return newRegistration;
	}
}
