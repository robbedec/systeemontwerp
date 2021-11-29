package be.ugent.systemdesign.university.curriculum.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {
	
	public final static String CURRICULUM_EVENT = "curriculum_event";
	public final static String REGISTER_NEW_ENROLLMENT = "register_new_enrollment";
	
	@Output(CURRICULUM_EVENT)
	MessageChannel curriculumEvent();
	
	@Input(REGISTER_NEW_ENROLLMENT)
	MessageChannel registerNewEnrollment();
	
}
