package be.ugent.systemdesign.university.faculty.API.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.domain.FacultyRepository;
import be.ugent.systemdesign.university.faculty.infrastructure.CourseRepublisher;


@RestController
@RequestMapping(path="api/facultydb/")
@CrossOrigin(origins="*")
public class FacultyDbController {
	
	@Autowired
	CourseRepublisher pub;
	
	Logger logger = LoggerFactory.getLogger(FacultyDbController.class);


	// this method was added to circumvent the race conditions for the learning platform service with the faculty service which sends out course events when starting up
	@GetMapping
	public String publishAllCourses() {
		try {
			logger.info("a call was made to republish the faculty events");
			pub.publishAll();
			return "publish successful";
		} catch (RuntimeException e){
			return "publish failure";
		}
	}
}
