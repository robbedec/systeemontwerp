package be.ugent.systemdesign.university.registration.application.query;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationReadModel {
	
	private String email;
	private String name;
	private String firstName;
	private LocalDate dateOfBirth;
	private String course;
	private boolean isAccepted;
}