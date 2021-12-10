package be.ugent.systemdesign.university.registration.domain;

import be.ugent.systemdesign.university.registration.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class RegistrationAcceptedEvent extends DomainEvent {
		
	private String accountId;
	private String email;
	private String name;
	private String firstName;
	private String faculty;
	private String degree;
	
	public RegistrationAcceptedEvent(String accountId, String email, String name, String firstName, String faculty, String degree) {
		super();
		this.accountId = accountId;
		this.email = email;
		this.name = name;
		this.firstName = firstName;
		this.faculty = faculty;
		this.degree = degree;
	}
}
