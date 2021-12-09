package be.ugent.systemdesign.university.faculty.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import be.ugent.systemdesign.university.faculty.domain.seedwork.AggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "degrees")
public class DegreeProgramme extends AggregateRoot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long degreeId;
	
	private String degreeName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "degree", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Course> availableCourses = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;
	
	public DegreeProgramme(String _name, Faculty _f) {
		this.degreeName = _name;
		this.faculty = _f;
	}
	
	public void addCourse(Course _course) {
		availableCourses.add(_course);
		addDomainEvent(new FacultyCoursesChangedDomainEvent(FacultyCoursesChangeType.ADDED, faculty.getFacultyName(), this.degreeName, _course.getCourseName(), _course.getCourseCredits()));
	}
	
	public void removeCourse(Course _course) {
		availableCourses.remove(_course);
		addDomainEvent(new FacultyCoursesChangedDomainEvent(FacultyCoursesChangeType.REMOVED, faculty.getFacultyName(), this.degreeName, _course.getCourseName(), _course.getCourseCredits()));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		return this.degreeName.equals(((DegreeProgramme) obj).getDegreeName());
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(this.degreeName);
	}
}
