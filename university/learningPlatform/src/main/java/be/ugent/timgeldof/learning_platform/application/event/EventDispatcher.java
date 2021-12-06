package be.ugent.timgeldof.learning_platform.application.event;

import be.ugent.timgeldof.learning_platform.domain.course.AddedCourseAnnouncementDomainEvent;
import be.ugent.timgeldof.learning_platform.domain.course.PublishedCourseMaterialVisibleDomainEvent;
import be.ugent.timgeldof.learning_platform.domain.seedwork.DomainEvent;

public interface EventDispatcher {
	void publishCourseAnnouncementEvent(AddedCourseAnnouncementDomainEvent event);
	void publishCourseMaterialVisibleEvent(PublishedCourseMaterialVisibleDomainEvent event);
}
