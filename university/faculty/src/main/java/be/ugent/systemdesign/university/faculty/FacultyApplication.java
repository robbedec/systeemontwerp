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
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.cloud.stream.annotation.EnableBinding;

import be.ugent.systemdesign.university.faculty.API.messaging.Channels;
import be.ugent.systemdesign.university.faculty.API.rest.CourseViewModel;
import be.ugent.systemdesign.university.faculty.API.rest.FacultyViewModel;
import be.ugent.systemdesign.university.faculty.application.FacultyService;
import be.ugent.systemdesign.university.faculty.application.Response;
import be.ugent.systemdesign.university.faculty.domain.Course;
import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.domain.FacultyRepository;
import be.ugent.systemdesign.university.faculty.infrastructure.FacultyJPARepository;

@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class FacultyApplication {
	
	Logger logger = LoggerFactory.getLogger(FacultyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FacultyApplication.class, args);
	}
	
	@Bean
	CommandLineRunner seedDatabase(FacultyRepository repo){ 
		return(args)->{
			logger.info("**ADD FACULTIES**");
			
			Faculty iw_fac = new Faculty("Ingenieurswetenschappen & architectuur");
			Faculty wet_fac = new Faculty("Wetenschappen");
			Faculty dier_fac = new Faculty("Dierengeneeskunde");
			
			for (Faculty f : Arrays.asList(iw_fac, wet_fac, dier_fac)) {
				repo.save(f);
			}
		};
	}
	
	@Bean
	CommandLineRunner addCourseToFaculty(FacultyService service, FacultyRepository repo){ 
		return(args)->{
			logger.info("**ADD COURSES TRHOUGH SERVICE**");
			
			
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
				service.addCourseToFaculty((long) 1, item.getKey(), item.getValue());
			}
		};
	}	
	
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
}
