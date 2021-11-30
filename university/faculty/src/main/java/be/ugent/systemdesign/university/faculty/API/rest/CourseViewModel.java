package be.ugent.systemdesign.university.faculty.API.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseViewModel {
	String courseId;
	String courseName;
	String courseCredits;
}
