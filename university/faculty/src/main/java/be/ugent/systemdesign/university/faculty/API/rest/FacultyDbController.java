package be.ugent.systemdesign.university.faculty.API.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.university.faculty.infrastructure.ReseedDatabase;


@RestController
@RequestMapping(path="api/facultydb/")
@CrossOrigin(origins="*")
public class FacultyDbController {
	
	@Autowired
	ReseedDatabase db;
	// this method was added to circumvent the race conditions for the learning platform service with the faculty service which sends out course events when starting up
	@GetMapping
	public String reseedDatabase() {
		db.reseedDatabase();
		return "seed successful";
	}
}
