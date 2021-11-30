package be.ugent.systemdesign.university.faculty.API.rest;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FacultyViewModel {
	
	String facultyId;
	String facultyName;
	List<CourseViewModel> available_courses;
}
