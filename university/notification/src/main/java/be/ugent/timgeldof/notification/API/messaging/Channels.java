package be.ugent.timgeldof.notification.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface Channels{
	
	public final static String CURRICULUM_CHANGED_EVENT = "curriculum_changed_event";
	public final static String COURSE_MATERIAL_VISIBILITY_EVENT = "course_material_visibility_event";
	public final static String NEW_REGISTRATION_EVENT = "new_registration_event";
	
	// INPUT 
	@Input(CURRICULUM_CHANGED_EVENT)
	SubscribableChannel noteCurriculumChange();

	@Input(COURSE_MATERIAL_VISIBILITY_EVENT)
	SubscribableChannel noteCourseMaterialChange();
	
	@Input(NEW_REGISTRATION_EVENT)
	SubscribableChannel noteNewRegistration();
	
}
