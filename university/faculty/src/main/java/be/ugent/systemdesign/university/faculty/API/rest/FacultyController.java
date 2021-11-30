package be.ugent.systemdesign.university.faculty.API.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.university.faculty.FacultyApplication;

@RestController
@RequestMapping(path="api/faculty/")
@CrossOrigin(origins="*")
public class FacultyController {
	
	@GetMapping
	public List<FacultyViewModel> findAll() {
		return FacultyApplication.getData();
	}
}
