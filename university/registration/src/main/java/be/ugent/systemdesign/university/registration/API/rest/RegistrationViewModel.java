package be.ugent.systemdesign.university.registration.API.rest;

import java.time.LocalDate;

import be.ugent.systemdesign.university.registration.application.query.RegistrationReadModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationViewModel {

	private String registrationId;
	private String accountId;
	private String registrationDate;
	private String email;
	private String name;
	private String firstName;
	private String dateOfBirth;
	private String faculty;
	private String degree;
	private String status;
	
	public RegistrationViewModel(RegistrationReadModel r) {
		accountId = r.getAccountId();
		registrationDate = r.getRegistrationDate().toString();
		email = r.getEmail();
		name = r.getName();
		firstName = r.getFirstName();
		dateOfBirth = r.getDateOfBirth().toString();
		faculty = r.getFaculty();
		degree = r.getDegree();
		status = r.getStatus();
	}
}
