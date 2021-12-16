package be.ugent.systemdesign.university.registration.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
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
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer registrationId;
	private String accountId;
	private Date registrationDate;
	private String email;
	private String name;
	private String firstName;
	private LocalDate dateOfBirth;
	private String faculty;
	private String degree;
	private Status status;	
	private Integer numberOfOpenViolations;
	private Boolean isActive;
	
	
	public Registration(Date _registrationDate, String _email, String _name, String _firstName, LocalDate _dateOfBirth, String _faculty, String _degree) {
		this.registrationDate = _registrationDate;		
		setEmail(_email);
		this.name = _name;
		this.firstName = _firstName;
		setDateOfBirth(_dateOfBirth);		
		this.faculty = _faculty;
		this.degree = _degree;
		this.status = Status.SUBMITTED;
		this.numberOfOpenViolations = 0;	//can't create a new registration with open violations
		this.isActive = false;
	}
	
	public void accept () {
		this.status = Status.ACCEPTED;
		this.isActive = true;
		addDomainEvent(new RegistrationAcceptedEvent(accountId, email, faculty, degree));
	}
	
	public void reject() {
		this.status = Status.DENIED;		
		//notification service aanspreken
	}
	
	public void noteLatePayment() {
		this.status = status.ACCEPTED_LATE_PAYMENT;
	}
	
	public void noteNewViolation() {
		this.numberOfOpenViolations++;
	}
	
	public void notePayment() {
		this.status = Status.ACCEPTED_PAID;		
	}
	
	public boolean isPaid() {
		return this.status == Status.ACCEPTED_PAID;
	}
	
	public boolean hasOpenViolations() {
		return this.numberOfOpenViolations > 0;
	}
	
	public void setEmail(String _email) {
		if(!_email.contains("@")) {
			throw new InvalidRegistrationException();
		}
		this.email = _email;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		if(dateOfBirth.isAfter(LocalDate.now())) {
			throw new InvalidRegistrationException();
		}
	}

	
}
