package be.ugent.systemdesign.university.faculty.domain;

import be.ugent.systemdesign.university.faculty.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class FacultyCourseAddedOrRemoved extends DomainEvent {
	
	private FacultyCourseChanges changeType;
	private String facultyName;
	private String courseName;
	private Integer courseCredits;
	
	public FacultyCourseAddedOrRemoved(FacultyCourseChanges _type, String _fname, String _cname, Integer credits) {
		super();
		this.changeType = _type;
		this.facultyName = _fname;
		this.courseName = _cname;
		this.courseCredits = credits;
	}
	
}
