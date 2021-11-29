package be.ugent.systemdesign.university.curriculum.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.university.curriculum.CurriculumApplication;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumChangedDomainEvent;


@Service
public class CurriculumEventListener {
	
	private static final Logger log = LoggerFactory.getLogger(CurriculumApplication.class);
	
	@Autowired
	EventDispatcher eventDispatcher;
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleInpatientHasLeftSync(CurriculumChangedDomainEvent event) {
		log.info(">handle curriculumChanged Sync of event created at {}, with new status {}", event.getCreatedTime(), buildCourseLog(event));
	}
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleInpatientHasLeftAsync(CurriculumChangedDomainEvent event) {
		log.info(">handle curriculumChanged Async of event created at {}, with new status {}", event.getCreatedTime(), buildCourseLog(event));
		eventDispatcher.publishCurriculumEvent(event);
	}
	
	private String buildCourseLog(CurriculumChangedDomainEvent event) {
		StringBuilder sb = new StringBuilder();
		event.getCourses().forEach(c -> sb
				.append(c.getName())
				.append(" (")
				.append(c.getCredits())
				.append("),"));
		
		return sb.toString();
	}
}
