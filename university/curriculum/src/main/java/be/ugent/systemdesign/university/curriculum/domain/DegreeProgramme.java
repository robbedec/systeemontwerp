package be.ugent.systemdesign.university.curriculum.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DegreeProgramme {
	
	private String degreeName;
	private List<Course> availableCourses;
	
	public DegreeProgramme(String degreeName) {
		this.degreeName = degreeName;
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
