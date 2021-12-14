package be.ugent.timgeldof.learning_platform.API.messaging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.timgeldof.learning_platform.application.event.EventHandler;
import be.ugent.timgeldof.learning_platform.application.event.FacultyCoursesChangedEvent;
import be.ugent.timgeldof.learning_platform.application.event.PaymentOverdueEvent;
import be.ugent.timgeldof.learning_platform.application.event.PlagiarismRegisteredEvent;
import be.ugent.timgeldof.learning_platform.domain.course_access.CurriculumChangedDomainEvent;

@Component
public class MessageInputGateway {

	private static final Logger log = LoggerFactory.getLogger(MessageInputGateway.class);

	
	@Autowired
	EventHandler eventHandler;
	
	@StreamListener(Channels.FACULTY_EVENT)
	public void consumeFacultyCoursesChangedEvent(FacultyCoursesChangedEvent event) {
		log.info("Message input gateway received FACULTY_EVENT of type " + event.getChangeType());
		eventHandler.handleFacultyCoursesChanged(event);
	}
	
	@StreamListener(Channels.CURRICULUM_CHANGED_EVENT)
	public void consumeCurriculumChangedEvent(CurriculumChangedDomainEvent event) {
		log.info("Message input gateway received CURRICULUM_CHANGED_EVENT of type " + event.getChangeType());
		eventHandler.handleCurriculumChanged(event);
	}
	
	@StreamListener(Channels.PAYMENT_OVERDUE_EVENT)
	public void consumePaymentOverdue(PaymentOverdueEvent event) {
		log.info("Message input gateway received INVOICE_PAID_EVENT of student: " + event.getStudentNumber());
		eventHandler.handlePaymentOverdue(event);
	}
	
	@StreamListener(Channels.PLAGIARISM_REGISTERED_EVENT)
	public void consumePlagiarismRegisteredEvent(PlagiarismRegisteredEvent event) {
		log.info("Message input gateway received facultyCourseChangedEvent for student with id: " + event.getStudentId().toString());
		eventHandler.handlePlagiarism(event);
	}
	
}
