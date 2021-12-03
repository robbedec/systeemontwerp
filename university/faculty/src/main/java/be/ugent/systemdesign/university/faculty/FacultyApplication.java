package be.ugent.systemdesign.university.faculty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.university.faculty.API.rest.CourseViewModel;
import be.ugent.systemdesign.university.faculty.API.rest.FacultyViewModel;
import be.ugent.systemdesign.university.faculty.application.FacultyService;
import be.ugent.systemdesign.university.faculty.application.Response;
import be.ugent.systemdesign.university.faculty.domain.Course;
import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.domain.FacultyRepository;
import be.ugent.systemdesign.university.faculty.infrastructure.FacultyJPARepository;

@SpringBootApplication
public class FacultyApplication {
	
	Logger logger = LoggerFactory.getLogger(FacultyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FacultyApplication.class, args);
	}
	
	@Bean
	CommandLineRunner seedDatabase(FacultyRepository repo){ 
		return(args)->{//call methods of repo and log output};
			logger.info("**SEEDING DATABASE**");
			
			Faculty iw_fac = new Faculty("Ingenieurswetenschappen & architectuur");
			Map<String, Integer> iw_courses = Map.ofEntries(
					entry("Wiskunde 1", 6),
					entry("Wiskunde 2", 6),
					entry("Gegevensstructuren", 3),
					entry("Systeemontwerp", 3),
					entry("Algoritmen", 6),
					entry("Gevorderde algoritmen", 6)
				);
			
			for (Map.Entry<String, Integer> item : iw_courses.entrySet()) {
				// Make sure to use the addCourse method so that domain events are created
				// to update other services that are interested.
				iw_fac.addCourse(new Course(item.getKey(), item.getValue(), iw_fac));
			}
			
			repo.save(iw_fac);
		};
	}
	
	/*
	@Bean
	CommandLineRunner testRepository(FacultyJPARepository repo){ 
		return(args)->{//call methods of repo and log output};
			logger.info("**TESTING IF FACULTIES ARE IN DATABASE**");
			for (Faculty faculty : repo.findAll()) {
				logger.info("$" + faculty.getFacultyName());
			}
		};
	}
	*/
	
	@Bean
	CommandLineRunner addCourseToFaculty(FacultyService service, FacultyRepository repo){ 
		return(args)->{//call methods of repo and log output};
			logger.info("**ADD COURSE TRHOUGH SERVICE**");
			
			service.addCourseToFaculty((long) 1, "test", 5);
		};
	}
	
	
	/*
	@Bean
	CommandLineRunner seedCoursesToDatabase(FacultyService service, FacultyJPARepository repo) {
		return (args) -> {
			logger.info("**ADDING COURSES TO FACULTIES**");
			// The id's of different faculties can be found in sr/main/resources/facultiesandcourses.sql
			
			//Faculty iw_fac = repo.getById(1);
			Response res;
			Map<String, Integer> iw_courses = Map.ofEntries(
				entry("Wiskunde 1", 6),
				entry("Wiskunde 2", 6),
				entry("Gegevensstructuren", 3),
				entry("Systeemontwerp", 3),
				entry("Algoritmen", 6),
				entry("Gevorderde algoritment", 6)
			);
			
			for (Map.Entry<String, Integer> item : iw_courses.entrySet()) {
				res = service.addCourseToFaculty(1, item.getKey(), item.getValue());
				//iw_fac.addCourse(new Course(item.getKey(), item.getValue()));
			}
			
			repo.flush();
			
			//logFaculty(repo.getById(1));
		};
	}
	*/
	
	/*
	@Bean
	CommandLineRunner readFaculty(FacultyRepository repo){ 
		return(args)->{//call methods of repo and log output};
			logger.info("**reading faculty**");
			
			Faculty f = repo.findByFacultyId(1);
			logFaculty(f);
		};
	}*/
	
	
	@Bean
	CommandLineRunner testFacultiesWithCourses(FacultyJPARepository repo){ 
		return(args)->{//call methods of repo and log output};
			logger.info("**READ FACULTIES WITH COURSES**");
			repo.flush();
			for (Faculty faculty : repo.findAll()) {
				logger.info("$" + faculty.getFacultyName());
				Integer csize = faculty.getAvailableCourses().size();
				for (Course c : faculty.getAvailableCourses()) {
					logger.info(">>" + c.getCourseName());
				}
			}
		};
	}
	
	//spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
	
	private void logFaculty(Faculty f) {
		
		StringBuilder sb = new StringBuilder();
		for (Course c : f.getAvailableCourses()) {
			sb.append(c.getCourseName());
			sb.append(" (");
			sb.append(c.getCourseCredits());
			sb.append(") ");
		}
		
		logger.info("--facultyId {};" +
				" facultyName {},  {}, courses {}.",
						f.getFacultyId(), f.getFacultyName(), sb.toString()); 
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
