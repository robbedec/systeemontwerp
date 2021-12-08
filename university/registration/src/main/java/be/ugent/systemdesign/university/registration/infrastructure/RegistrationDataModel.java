package be.ugent.systemdesign.university.registration.infrastructure;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import be.ugent.systemdesign.university.registration.domain.PayementStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegistrationDataModel {

	@Id
	private int registrationId;
	
	private Date registrationDate;
	private String email;
	private String name;
	private String firstName;
	private LocalDate dateOfBirth;
	private String course;
	private boolean isAccepted;
	private String payementStatus;
	
	public RegistrationDataModel(int registrationId, Date registrationDate, String email, String name, String firstName, 
			LocalDate dateOfBirth, String course, boolean isAccepted, PayementStatus payementStatus){
				this.registrationId = registrationId;
				this.registrationDate = registrationDate;
				this.email = email;
				this.name = name;
				this.firstName = firstName;
				this.dateOfBirth = dateOfBirth;
				this.course = course;
				this.isAccepted = isAccepted;
				this.payementStatus = payementStatus.name();
			}	
}
