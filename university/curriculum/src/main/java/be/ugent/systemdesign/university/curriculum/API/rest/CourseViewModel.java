package be.ugent.systemdesign.university.curriculum.API.rest;

import be.ugent.systemdesign.university.curriculum.application.query.CourseReadModel;
import lombok.Getter;

@Getter
public class CourseViewModel {
	
	private String name;
	private String credits;
	
	public CourseViewModel(CourseReadModel _crm) {
		this.name = _crm.getName();
		this.credits = _crm.getCredits().toString();
	}
}
