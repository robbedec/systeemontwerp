package be.ugent.systemdesign.university.registration.domain;

import be.ugent.systemdesign.university.registration.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class RegistrationAcceptedEvent extends DomainEvent {
	//studentid ?
	private String registrationId;
	private String email;
	private String name;
	private String firstName;
	private String course;
	
	public RegistrationAcceptedEvent(String registrationId, String email, String name, String firstName, String course) {
		super();
		this.registrationId = registrationId;
		this.email = email;
		this.name = name;
		this.firstName = firstName;
		this.course = course;
	}
}
