package be.ugent.systemdesign.university.curriculum.domain;

import be.ugent.systemdesign.university.curriculum.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class CurriculumChangedDomainEvent extends DomainEvent {
	
	private String studentId;
	private Integer courseId;
	private String courseName;
	private String courseCredits;
	private String changeType;
	
	public CurriculumChangedDomainEvent(String _studentId, Integer _courseId, String _courseName, Integer _courseCredits, FacultyCourseChangeType _changeType) {
		super();
		this.studentId = _studentId;
		this.courseId = _courseId;
		this.courseName = _courseName;
		this.courseCredits = _courseCredits.toString();
		this.changeType = _changeType.name();
	}
}
