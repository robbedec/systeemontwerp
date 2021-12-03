package be.ugent.systemdesign.university.faculty.API.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.university.faculty.FacultyApplication;
import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.domain.FacultyRepository;
import be.ugent.systemdesign.university.faculty.infrastructure.FacultyJPARepository;

@RestController
@RequestMapping(path="api/faculty/")
@CrossOrigin(origins="*")
public class FacultyController {
	
	@Autowired
	FacultyJPARepository repo;
	
	@GetMapping
	public List<Faculty> findAll() {
		//return FacultyApplication.getData();
		return repo.findAll();
	}
}
