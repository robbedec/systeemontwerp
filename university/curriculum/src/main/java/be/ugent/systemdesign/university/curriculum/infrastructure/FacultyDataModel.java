package be.ugent.systemdesign.university.curriculum.infrastructure;

import java.time.LocalDate;
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
public class FacultyDataModel {
	
	@Id
	private String facultyId;
	private String facultyName;
	private List<CourseDataModel> available_courses;
	
	public FacultyDataModel(String _facultyId, String _facultyName, List<Course> _availableCourses) {
		this.facultyId = _facultyId;
		this.facultyName = _facultyName;
		this.available_courses = _availableCourses.stream().map(c -> new CourseDataModel(c.getName(), c.getCredits())).collect(Collectors.toList());
	}
}
