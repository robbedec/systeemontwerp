package be.ugent.systemdesign.university.curriculum.application.event;

import be.ugent.systemdesign.university.curriculum.domain.CurriculumChangedDomainEvent;

public interface EventDispatcher {

	void publishCurriculumEvent(CurriculumChangedDomainEvent event);
}
