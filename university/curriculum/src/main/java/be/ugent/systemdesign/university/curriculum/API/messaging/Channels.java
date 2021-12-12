package be.ugent.systemdesign.university.curriculum.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	
	public final static String CURRICULUM_CHANGED_EVENT = "curriculum_changed_event";
	public final static String CURRICULUM_REVIEWED_EVENT = "curriculum_reviewed_event";
	public final static String NEW_REGISTRATION_EVENT = "new_registration_event";
	public final static String DISENROLLMENT_EVENT = "disenrollment_event";
	public final static String FACULTY_EVENT = "faculty_event";
	
	@Output(CURRICULUM_CHANGED_EVENT)
	MessageChannel curriculumChangedEvent();
	
	@Output(CURRICULUM_REVIEWED_EVENT)
	MessageChannel curriculumReviewedEvent();
	
	@Input(NEW_REGISTRATION_EVENT)
	SubscribableChannel registerNewEnrollment();
	
	@Input(DISENROLLMENT_EVENT)
	SubscribableChannel registerDisenrollment();
	
	@Input(FACULTY_EVENT)
	SubscribableChannel noteFacultyCoursesChange();
}
