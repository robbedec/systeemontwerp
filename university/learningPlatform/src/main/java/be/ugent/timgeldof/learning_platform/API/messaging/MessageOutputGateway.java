package be.ugent.timgeldof.learning_platform.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.timgeldof.learning_platform.application.event.EventDispatcher;
import be.ugent.timgeldof.learning_platform.domain.course.AddedCourseAnnouncementDomainEvent;
import be.ugent.timgeldof.learning_platform.domain.course.PublishedCourseMaterialVisibleDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {
	
	@Gateway(requestChannel = Channels.COURSE_MATERIAL_VISIBILITY_EVENT)
	void publishCourseMaterialVisibleEvent(PublishedCourseMaterialVisibleDomainEvent event);
	
	@Gateway(requestChannel = Channels.NEW_ANNOUNCEMENT_EVENT)
	void publishCourseAnnouncementEvent(AddedCourseAnnouncementDomainEvent event);
}