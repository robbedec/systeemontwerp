package be.ugent.systemdesign.university.faculty.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.university.faculty.application.event.EventDispatcher;
import be.ugent.systemdesign.university.faculty.domain.FacultyCoursesChangedDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {
	
	@Gateway(requestChannel = Channels.FACULTY_EVENT)
	void publishFacultyEvent(FacultyCoursesChangedDomainEvent event);
}
