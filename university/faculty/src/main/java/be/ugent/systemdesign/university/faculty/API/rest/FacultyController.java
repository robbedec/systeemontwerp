package be.ugent.systemdesign.university.faculty.API.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.university.faculty.domain.Course;
import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.infrastructure.FacultyJPARepository;

@RestController
@RequestMapping(path="api/faculty/")
@CrossOrigin(origins="*")
public class FacultyController {
	
	@Autowired
	FacultyJPARepository repo;
	
	@GetMapping
	public List<FacultyViewModel> findAll() {
		return repo.findAll().stream().map(fac -> new FacultyViewModel(fac.getFacultyId().toString(), fac.getFacultyName(), mapCoursesToViewmodel(fac.getAvailableCourses()))).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public FacultyViewModel findByFacultyId(@PathVariable("id") String facultyId) {
		Faculty f = repo.getById(Long.parseLong(facultyId));
		return new FacultyViewModel(f.getFacultyId().toString(), f.getFacultyName(), mapCoursesToViewmodel(f.getAvailableCourses()));
	}
	
	/*
	 * http://localhost/api/faculty/all?facultyName=Ingenieurswetenschappen+%26+architectuur
	 * Voor encodings zie: https://www.w3schools.com/tags/ref_urlencode.asp
	 */
	@GetMapping("all")
	public FacultyViewModel findByFacultyName(@RequestParam("facultyName") String facultyName) {
		Faculty f = repo.findByFacultyName(facultyName);
		return new FacultyViewModel(f.getFacultyId().toString(), f.getFacultyName(), mapCoursesToViewmodel(f.getAvailableCourses()));
		
	}
	
	private List<CourseViewModel> mapCoursesToViewmodel(List<Course> cl) {
		return cl.stream().map(c -> new CourseViewModel(c.getId().toString(), c.getCourseName(), c.getCourseCredits().toString())).collect(Collectors.toList()); 
	}
}
