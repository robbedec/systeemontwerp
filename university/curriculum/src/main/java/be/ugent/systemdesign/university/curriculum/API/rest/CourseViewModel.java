package be.ugent.systemdesign.university.curriculum.API.rest;

import be.ugent.systemdesign.university.curriculum.application.query.CourseReadModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseViewModel {
	
	private Integer courseId;
	private String name;
	private String credits;
	
	public CourseViewModel(CourseReadModel _crm) {
		this.courseId = _crm.getCourseId();
		this.name = _crm.getName();
		this.credits = _crm.getCredits().toString();
	}
}
