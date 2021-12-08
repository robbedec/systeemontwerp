package be.ugent.systemdesign.university.curriculum.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.university.curriculum.application.event.EventDispatcher;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumChangedDomainEvent;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumReviewedDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {

	@Gateway(requestChannel = Channels.CURRICULUM_CHANGED_EVENT)
	void publishCurriculumChangedEvent(CurriculumChangedDomainEvent event);
	
	@Gateway(requestChannel = Channels.CURRICULUM_REVIEWED_EVENT)
	void publishCurriculumReviewedEvent(CurriculumReviewedDomainEvent event);
}
