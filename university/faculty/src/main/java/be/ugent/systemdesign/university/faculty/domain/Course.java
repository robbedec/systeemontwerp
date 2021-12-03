package be.ugent.systemdesign.university.faculty.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	private Integer id;
	private String courseName;
	private Integer courseCredits;
	
	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;
	
	public boolean equals(Course obj) {
		if (obj == null) return false;
		return this.courseName == obj.getCourseName() && this.courseCredits == obj.getCourseCredits();
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(this.courseName, this.courseCredits);
	}
}
