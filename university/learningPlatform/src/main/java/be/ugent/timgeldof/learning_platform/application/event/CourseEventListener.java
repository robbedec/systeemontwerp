package be.ugent.timgeldof.learning_platform.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.timgeldof.learning_platform.domain.course.AddedCourseAnnouncementDomainEvent;
import be.ugent.timgeldof.learning_platform.domain.course.PublishedCourseMaterialVisibleDomainEvent;

@Service
public class CourseEventListener {
	private static final Logger log = LoggerFactory.getLogger(CourseEventListener.class);

	@Autowired
	EventDispatcher eventDispatcher;
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleAddedCourseAnnouncementAsync(AddedCourseAnnouncementDomainEvent event) {
		log.info("event handle: added Course Announcement {} at {}", event.getAnnouncementMessage(),event.getCreatedTime());
		eventDispatcher.publishCourseAnnouncementEvent(event);
	}
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleMadeCourseMaterialVisibleAsync(PublishedCourseMaterialVisibleDomainEvent event) {
		log.info("event handle: added course material for {} at {}", event.getCourseName(),event.getCreatedTime());
		eventDispatcher.publishCourseMaterialVisibleEvent(event);
	}
	
}
