package be.ugent.systemdesign.university.faculty.domain;

import be.ugent.systemdesign.university.faculty.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class FacultyCoursesChangedDomainEvent extends DomainEvent {
	
	private Integer courseId;
	private String changeType;
	private String facultyName;
	private String degreeName;
	private String courseName;
	private Integer courseCredits;
	private Integer teacherId;
	
	public FacultyCoursesChangedDomainEvent(Integer _courseId, FacultyCoursesChangeType _type, String _fname, String _dname, String _cname, Integer credits, Integer _teacherId) {
		super();
		this.courseId = _courseId;
		this.changeType = _type.name();
		this.facultyName = _fname;
		this.degreeName = _dname;
		this.courseName = _cname;
		this.courseCredits = credits;
		this.teacherId = _teacherId;
	}
	
}
