package be.ugent.timgeldof.learning_platform.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.timgeldof.learning_platform.application.LearningPlatformService;
import be.ugent.timgeldof.learning_platform.domain.course_access.CurriculumChangedDomainEvent;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	LearningPlatformService s;
	
	public void handleFacultyCoursesChanged(FacultyCoursesChangedEvent event) {
		if(event.getChangeType().equalsIgnoreCase("ADDED"))
			s.addCourse(event.getCourseName(), event.getCourseCredits(), event.getTeacherId());
		else
			s.removeCourse(event.getCourseName(), event.getCourseCredits());
	}

	public void handleCurriculumChanged(CurriculumChangedDomainEvent event) {
		s.changeCurriculum(event.getChangeType(), event.getStudentId(), event.getCourseCredits(), event.getCourseName());
	}

	public void handleInvoicePaid(InvoicePaidEvent event) {
		s.registerInvoicePaid(event.getStudentId());
	}

	public void handlePlagiarism(PlagiarismRegisteredEvent event) {
		s.registerPlagiarism(event.getStudentId(), event.getChangeType());
	}
}
