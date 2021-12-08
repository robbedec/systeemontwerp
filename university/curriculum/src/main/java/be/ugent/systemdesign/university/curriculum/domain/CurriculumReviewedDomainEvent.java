package be.ugent.systemdesign.university.curriculum.domain;

import be.ugent.systemdesign.university.curriculum.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class CurriculumReviewedDomainEvent extends DomainEvent {

	private String studentId;
	private String status;
	
	public CurriculumReviewedDomainEvent(String _studentId, CurriculumStatus _status) {
		super();
		this.studentId = _studentId;
		this.status = _status.name();
	}
}
