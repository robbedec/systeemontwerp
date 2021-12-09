package be.ugent.systemdesign.university.faculty.domain;

import be.ugent.systemdesign.university.faculty.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class FacultyCoursesChangedDomainEvent extends DomainEvent {
	
	private FacultyCoursesChangeType changeType;
	private String facultyName;
	private String degreeName;
	private String courseName;
	private Integer courseCredits;
	
	public FacultyCoursesChangedDomainEvent(FacultyCoursesChangeType _type, String _fname, String _dname, String _cname, Integer credits) {
		super();
		this.changeType = _type;
		this.facultyName = _fname;
		this.degreeName = _dname;
		this.courseName = _cname;
		this.courseCredits = credits;
	}
	
}
