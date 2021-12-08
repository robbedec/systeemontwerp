package be.ugent.systemdesign.university.curriculum.application.event;

import be.ugent.systemdesign.university.curriculum.domain.CurriculumChangedDomainEvent;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumReviewedDomainEvent;

public interface EventDispatcher {

	void publishCurriculumChangedEvent(CurriculumChangedDomainEvent event);
	void publishCurriculumReviewedEvent(CurriculumReviewedDomainEvent event);
}
