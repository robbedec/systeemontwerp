package be.ugent.systemdesign.university.registration.API.rest;

import java.time.LocalDate;

import be.ugent.systemdesign.university.registration.application.query.RegistrationReadModel;
import lombok.Getter;

@Getter
public class RegistrationViewModel {

	private String email;
	private String name;
	private String firstName;
	private LocalDate dateOfBirth;
	private String course;
	private boolean isAccepted;
	
	public RegistrationViewModel(RegistrationReadModel r) {
		email = r.getEmail();
		name = r.getName();
		firstName = r.getFirstName();
		dateOfBirth = r.getDateOfBirth();
		course = r.getCourse();
		isAccepted = r.isAccepted();
	}
}
