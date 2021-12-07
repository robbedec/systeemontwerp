package be.ugent.timgeldof.learning_platform.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	public final static String NEW_ANNOUNCEMENT_EVENT = "new_announcement_event";
	public final static String COURSE_MATERIAL_VISIBILITY_EVENT = "course_material_visibility_event";
	public final static String FACULTY_EVENT = "faculty_event";

	@Output(NEW_ANNOUNCEMENT_EVENT)
	MessageChannel announcementEvent();
	
	@Output(COURSE_MATERIAL_VISIBILITY_EVENT)
	MessageChannel courseMaterialVisibilityEvent();
	
	@Input(FACULTY_EVENT)
	SubscribableChannel noteFacultyCoursesChange();
}
