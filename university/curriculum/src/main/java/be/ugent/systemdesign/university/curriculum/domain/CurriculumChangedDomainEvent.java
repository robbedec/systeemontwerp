package be.ugent.systemdesign.university.curriculum.domain;

import java.util.List;

import be.ugent.systemdesign.university.curriculum.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class CurriculumChangedDomainEvent extends DomainEvent {
	
	private String curriculumId;
	private List<Course> courses;
	
	public CurriculumChangedDomainEvent(String _curriculumId, List<Course> _courses) {
		super();
		this.curriculumId = _curriculumId;
		this.courses = _courses;
	}
}
