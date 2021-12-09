package be.ugent.systemdesign.university.faculty.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import be.ugent.systemdesign.university.faculty.domain.FacultyCoursesChangedDomainEvent;

@Service
public class FacultyEventListener {
	
	private static final Logger log = LoggerFactory.getLogger(FacultyEventListener.class);
	
	@Autowired
	EventDispatcher eventDispatcher;
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleFacultyCoursesChangedSync(FacultyCoursesChangedDomainEvent event) {
		log.info(">handle FacultyCoursesChanged Sync of event created at {}, for faculty: {}: {} with {} points ({})", 
				event.getCreatedTime(), 
				event.getFacultyName(), 
				event.getCourseName(),
				event.getCourseCredits().toString(),
				event.getChangeType().name()
		);
		
	}
	
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleFacultyCoursesChangedAsync(FacultyCoursesChangedDomainEvent event) {
		log.info(">handle FacultyCoursesChanged Async of event created at {}, for faculty: {} ({}): {} with {} points ({})", 
				event.getCreatedTime(), 
				event.getFacultyName(), 
				event.getDegreeName(),
				event.getCourseName(),
				event.getCourseCredits().toString(),
				event.getChangeType().name()
		);
		
		eventDispatcher.publishFacultyEvent(event);
	}
}
