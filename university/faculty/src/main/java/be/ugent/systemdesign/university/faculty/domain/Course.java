package be.ugent.systemdesign.university.faculty.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String courseName;
	private Integer courseCredits;
	
	@ManyToOne
	@JoinColumn(name = "degree_id")
	private DegreeProgramme degree;
	
	public Course(String _courseName, Integer _courseCredits, DegreeProgramme _degree) {
		this.courseName = _courseName;
		this.courseCredits = _courseCredits;
		this.degree = _degree;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		return this.courseName.equals(((Course) obj).getCourseName()) && this.courseCredits == ((Course) obj).getCourseCredits();
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(this.courseName, this.courseCredits);
	}
}
