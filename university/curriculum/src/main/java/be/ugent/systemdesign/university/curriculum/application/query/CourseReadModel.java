package be.ugent.systemdesign.university.curriculum.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseReadModel {
	
	private Integer courseId;
	private String name;
	private Integer credits;
}
