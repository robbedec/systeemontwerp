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
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer registrationId;
	
	private String accountId;
	private Date registrationDate;
	private String email;
	private String name;
	private String firstName;
	private LocalDate dateOfBirth;	
	private String faculty;
	private String degree;
	private String status;
	
	public RegistrationDataModel(Integer registrationId, String accountId, Date registrationDate, String email, String name, String firstName, 
			LocalDate dateOfBirth, String faculty, String degree, Status status){		
				this.registrationId = registrationId;
				this.accountId = accountId;
				this.registrationDate = registrationDate;
				this.email = email;
				this.name = name;
				this.firstName = firstName;
				this.dateOfBirth = dateOfBirth;
				this.faculty = faculty;
				this.degree = degree;
				this.status = status.name();				
			}	
}
