package be.ugent.systemdesign.university.registration.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import be.ugent.systemdesign.university.registration.domain.seedwork.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Registration extends AggregateRoot{
	
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int registrationId;
	private Date registrationDate;
	private String email;
	private String name;
	private String firstName;
	private LocalDate dateOfBirth;
	private String course;
	private boolean isAccepted;
	private PayementStatus payementStatus;
	
	public Registration(Date _registrationDate, String _email, String _name, String _firstName, LocalDate _dateOfBirth, String _course) {
		this.registrationDate = _registrationDate;
		this.email = _email;
		this.name = _name;
		this.firstName = _firstName;
		this.dateOfBirth = _dateOfBirth;
		this.course = _course;
		this.isAccepted = false;
		this.payementStatus = PayementStatus.PENDING;
	}
	
	public void accept () {
		this.isAccepted = true;
		//addDomainEvent();
		//event versturen
		//mail laten versturen
		//account aanmaken
	}
	
	public void reject() {
		this.isAccepted = false; //mss veranderen naar ENUM?
		//mail versturen;
	}
}
