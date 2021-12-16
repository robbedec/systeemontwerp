package be.ugent.systemdesign.university.registration.API.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewRegistrationViewModel {
	private String accountId;
	private String email;
	private String name;
	private String firstName;
	private String dateOfBirth;
	private String faculty;
	private String degree;
}
