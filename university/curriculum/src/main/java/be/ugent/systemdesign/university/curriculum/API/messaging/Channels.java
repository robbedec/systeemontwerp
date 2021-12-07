package be.ugent.systemdesign.university.curriculum.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	
	public final static String CURRICULUM_CHANGED_EVENT = "curriculum_changed_event";
	public final static String REGISTER_NEW_ENROLLMENT = "register_new_enrollment";
	public final static String FACULTY_EVENT = "faculty_event";
	
	@Output(CURRICULUM_CHANGED_EVENT)
	MessageChannel curriculumChangedEvent();
	
	@Input(REGISTER_NEW_ENROLLMENT)
	MessageChannel registerNewEnrollment();
	
	@Input(FACULTY_EVENT)
	SubscribableChannel noteFacultyCoursesChange();
}
