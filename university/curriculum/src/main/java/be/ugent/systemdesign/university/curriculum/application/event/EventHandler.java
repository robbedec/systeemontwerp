package be.ugent.systemdesign.university.curriculum.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.university.curriculum.application.CurriculumService;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	CurriculumService curriculumService;
	
	public void handleFacultyCoursesChanged(FacultyCoursesChangedEvent event) {
		// todo: handle change in service
		
		log.info("-response status[{}] message[{}]", "", "");
	}
}
