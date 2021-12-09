package be.ugent.systemdesign.university.curriculum.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import be.ugent.systemdesign.university.curriculum.domain.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
public class DegreeProgrammeDataModel {
	
	private String degreeName;
	private List<CourseDataModel> available_courses;
	
	public DegreeProgrammeDataModel(String _name, List<Course> _courses) {
		this.degreeName = _name;
		this.available_courses = _courses.stream().map(c -> new CourseDataModel(c.getName(), c.getCredits())).collect(Collectors.toList());
	}
}
