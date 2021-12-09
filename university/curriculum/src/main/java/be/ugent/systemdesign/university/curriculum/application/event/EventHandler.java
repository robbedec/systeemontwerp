package be.ugent.systemdesign.university.curriculum.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.university.curriculum.application.CurriculumService;
import be.ugent.systemdesign.university.curriculum.application.Response;
import be.ugent.systemdesign.university.curriculum.domain.FacultyCourseChangeType;

@Service
public class EventHandler {

	private static final Logger log = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	CurriculumService curriculumService;
	
	public void handleFacultyCoursesChanged(FacultyCoursesChangedEvent event) {
		Response resp = curriculumService.noteFacultyCoursesChanged(event.getFacultyName(), event.getDegreeName(), FacultyCourseChangeType.valueOf(event.getChangeType()), event.getCourseName(), event.getCourseCredits());
		log.info("-response status[{}] message[{}]", resp.status, resp.message);
	}
}
