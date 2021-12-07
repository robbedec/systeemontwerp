package be.ugent.timgeldof.learning_platform.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.timgeldof.learning_platform.application.LearningPlatformService;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	LearningPlatformService s;
	
	public void handleFacultyCoursesChanged(FacultyCoursesChangedEvent event) {
		if(event.getChangeType().equalsIgnoreCase("ADDED"))
			s.addCourse(event.getCourseName(), event.getCourseCredits());
		else
			s.removeCourse(event.getCourseName(), event.getCourseCredits());
		
		log.info("-response status[{}] message[{}]", "", "");
	}
}