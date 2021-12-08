package be.ugent.systemdesign.university.faculty.application.event;

import be.ugent.systemdesign.university.faculty.domain.FacultyCoursesChangedDomainEvent;

public interface EventDispatcher {

	void publishFacultyEvent(FacultyCoursesChangedDomainEvent event);
}
