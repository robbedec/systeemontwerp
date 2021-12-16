package be.ugent.systemdesign.university.registration.infrastructure;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import be.ugent.systemdesign.university.registration.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegistrationDataModel {

	@Id
	@Column(name="registration_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	private Boolean isActive;
	
	public RegistrationDataModel(Integer registrationId, String accountId, Date registrationDate, String email, String name, String firstName, 
			LocalDate dateOfBirth, String socialSecurityNumber, String faculty, String degree, Status status, boolean isActive){		
				this.registrationId = registrationId;
				this.accountId = accountId;
				this.registrationDate = registrationDate;
				this.email = email;
				this.name = name;
				this.firstName = firstName;
				this.dateOfBirth = dateOfBirth;
				this.socialSecurityNumber = socialSecurityNumber;
				this.faculty = faculty;
				this.degree = degree;
				this.status = status.name();	
				this.isActive = isActive;
			}	
}
