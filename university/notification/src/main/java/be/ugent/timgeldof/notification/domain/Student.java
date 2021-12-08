package be.ugent.timgeldof.notification.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
	private String studentId;
	private String emailAddress;
	private String firstName;
	private String lastName;
	
	public String getFullName(){
		return this.firstName + " " + this.lastName;
	}

}
