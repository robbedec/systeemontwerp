package be.ugent.systemdesign.university.curriculum.domain;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import be.ugent.systemdesign.university.curriculum.domain.seedwork.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Faculty {

	private String facultyId;
	private String facultyName;
	private List<Course> availableCourses;
	
	public Faculty(String facultyName) {
		this.facultyName = facultyName;
		this.availableCourses = new ArrayList<>();
	}
	
	public void addCourse(String _name, Integer _credits) {
		this.availableCourses.add(Course.builder()
				.name(_name)
				.credits(_credits)
				.build()
			);
	}
	
	public void removeCourse(String _name, Integer _credits) {
		this.availableCourses.remove(new Course(_name, _credits));
	}
}
