package be.ugent.systemdesign.university.faculty.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import be.ugent.systemdesign.university.faculty.domain.seedwork.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "faculties")
public class Faculty extends AggregateRoot {
	
	@Id
	private Integer facultyId;
	private String facultyName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "faculty")
	private List<Course> availableCourses;
	
	public void addCourse(Course _course) {
		availableCourses.add(_course);
		addDomainEvent(new FacultyCourseAddedOrRemoved(FacultyCourseChanges.ADDED, facultyName, _course.getCourseName(), _course.getCourseCredits()));
	}
	
	public void removeCourse(Course _course) {
		availableCourses.remove(_course);
		addDomainEvent(new FacultyCourseAddedOrRemoved(FacultyCourseChanges.REMOVED, facultyName, _course.getCourseName(), _course.getCourseCredits()));
	}
}
