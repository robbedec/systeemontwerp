package be.ugent.systemdesign.university.faculty;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.systemdesign.university.faculty.API.rest.CourseViewModel;
import be.ugent.systemdesign.university.faculty.API.rest.FacultyViewModel;
import be.ugent.systemdesign.university.faculty.domain.Course;
import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.infrastructure.FacultyJPARepository;

@SpringBootApplication
public class FacultyApplication {
	
	Logger logger = LoggerFactory.getLogger(FacultyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FacultyApplication.class, args);
	}
	
	@Bean
	CommandLineRunner testRepository(FacultyJPARepository repo){ 
		return(args)->{//call methods of repo and log output};
			for (Faculty faculty : repo.findAll()) {
				logger.info("$" + faculty.getFacultyName());
				
				for (Course c : faculty.getAvailableCourses()) {
					logger.info(">>" + c.getCourseName());
				}
			}
		};
	}
	
	public static List<FacultyViewModel> getData() {
		List<FacultyViewModel> data = new ArrayList<>();
		
		Integer facultyIdCounter = 0;
		Integer courseIdCounter = 0;
		
		/*
		 * Faculteit Ingenieurswetenschappen
		 */
		
		FacultyViewModel ingenieurswetenschappen = new FacultyViewModel(
				(++facultyIdCounter).toString(), 
				"Ingenieurswetenschappen & Architectuur",
				List.of(
					new CourseViewModel((++courseIdCounter).toString(), "Wiskunde 1", "6"),
					new CourseViewModel((++courseIdCounter).toString(), "Wiskunde 2", "6"),
					new CourseViewModel((++courseIdCounter).toString(), "Discrete Wiskunde", "3"),
					new CourseViewModel((++courseIdCounter).toString(), "Algoritmen", "6"),
					new CourseViewModel((++courseIdCounter).toString(), "Geavanceerde Algoritmen", "6"),
					new CourseViewModel((++courseIdCounter).toString(), "Gegevensstructuren", "3"),
					new CourseViewModel((++courseIdCounter).toString(), "Signalen & Systemen", "6"),
					new CourseViewModel((++courseIdCounter).toString(), "Signalen & Systemen 2", "6")
				)
		);
		
		data.addAll(List.of(ingenieurswetenschappen));
		
		return data;
	}
}
