package be.ugent.systemdesign.university.curriculum.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDataModel {
	
	private Integer courseId;
	private String name;
	private Integer credits;
}
