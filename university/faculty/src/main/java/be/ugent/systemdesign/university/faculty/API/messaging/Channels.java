package be.ugent.systemdesign.university.faculty.API.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {
	static final String FACULTY_EVENT = "faculty_event";
	
	@Output(Channels.FACULTY_EVENT)
	MessageChannel facultyEvent();
	
}
