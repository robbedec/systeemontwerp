package be.ugent.systemdesign.university.registration.application.query;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationReadModel {
	
	private Integer registrationId;
	private String accountId;
	private Date registrationDate;	
	private String email;
	private String name;
	private String firstName;
	private LocalDate dateOfBirth;
	private String socialSecurityNumber;
	private String faculty;
	private String degree;
	private String status;
}
