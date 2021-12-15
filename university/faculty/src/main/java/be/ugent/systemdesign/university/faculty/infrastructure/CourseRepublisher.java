package be.ugent.systemdesign.university.faculty.infrastructure;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.university.faculty.application.FacultyServiceImpl;
import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.domain.FacultyCoursesChangeType;
import be.ugent.systemdesign.university.faculty.domain.FacultyCoursesChangedDomainEvent;
import be.ugent.systemdesign.university.faculty.domain.FacultyRepository;


// THIS CLASS WAS ADDED TO AVOID THE RACE CONDITIONS BETWEEN THE SERVICES WHEN BOOTING UP THE FACULTY SERVICE
@Component
public class CourseRepublisher {
	@Autowired
	ApplicationEventPublisher eventPublisher;
	@Autowired
	FacultyRepository repo;
	
	Logger logger = LoggerFactory.getLogger(CourseRepublisher.class);

	
	public void publishAll(){
		List<Faculty> faculties = repo.findAll();
		faculties.forEach(f -> f.getDegrees().forEach(d -> d.availableCourses.forEach(c -> {
			FacultyCoursesChangedDomainEvent event = new FacultyCoursesChangedDomainEvent(c.getId().intValue(), FacultyCoursesChangeType.ADDED, f.getFacultyName(), d.getDegreeName(), c.getCourseName(), c.getCourseCredits(), c.getTeacherId(), d.getDegreeId().intValue());
			eventPublisher.publishEvent(event);
			logger.info("republished faculty event for course with ID " +  event.getCourseId());
		})));
	}

}
