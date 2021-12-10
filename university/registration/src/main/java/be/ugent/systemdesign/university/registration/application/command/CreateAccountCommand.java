package be.ugent.systemdesign.university.registration.application.command;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAccountCommand {
	
	private String email;
	private String name;
	private String firstname;	
	private LocalDate dateOfBirth;
	//private String socialSecurityNumber;
	
}
