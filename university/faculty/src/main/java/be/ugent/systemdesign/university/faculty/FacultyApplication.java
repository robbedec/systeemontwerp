package be.ugent.systemdesign.university.faculty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.security.auth.x500.X500Principal;

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
import be.ugent.systemdesign.university.faculty.application.event.EventDispatcher;
import be.ugent.systemdesign.university.faculty.domain.Course;
import be.ugent.systemdesign.university.faculty.domain.DegreeProgramme;
import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.domain.FacultyCoursesChangeType;
import be.ugent.systemdesign.university.faculty.domain.FacultyCoursesChangedDomainEvent;
import be.ugent.systemdesign.university.faculty.domain.FacultyRepository;
import be.ugent.systemdesign.university.faculty.infrastructure.FacultyJPARepository;

@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class FacultyApplication {
	
	Logger logger = LoggerFactory.getLogger(FacultyApplication.class);
	
	public static Long courseIdCounter = (long) 1;

	public static void main(String[] args) {
		SpringApplication.run(FacultyApplication.class, args);
	}
	
	@Bean
	CommandLineRunner seedDatabase(FacultyRepository repo){ 
		return(args)->{
			logger.info("**ADD FACULTIES AND DEGREES**");
			
			Faculty iw_fac = new Faculty("Ingenieurswetenschappen & architectuur");
			iw_fac.addDegree(new DegreeProgramme("Industrieel Ingenieur", iw_fac));
			iw_fac.addDegree(new DegreeProgramme("Burgerlijk Ingenieur", iw_fac));
					
			
			Faculty wet_fac = new Faculty("Wetenschappen");
			wet_fac.addDegree(new DegreeProgramme("Fysica & Sterrenkunde", wet_fac));
			wet_fac.addDegree(new DegreeProgramme("Wiskunde", wet_fac));
			
			Faculty dier_fac = new Faculty("Dierengeneeskunde");
			dier_fac.addDegree(new DegreeProgramme("Dierengeneeskunde", dier_fac));
			
			for (Faculty f : Arrays.asList(iw_fac, wet_fac, dier_fac)) {
				repo.save(f);
			}
		};
	}
	
	@Bean
	CommandLineRunner addCourseToFaculty(FacultyService service, FacultyRepository repo, EventDispatcher eventDispatcher){ 
		return(args)->{
			logger.info("**ADD COURSES TRHOUGH SERVICE**");
			
			Map<String, Integer> ind_ing = Map.ofEntries(
					entry("Wiskunde 1", 6),
					entry("Wiskunde 2", 6),
					entry("Algemene chemie", 6),
					entry("Elektriciteit", 6),
					entry("Gegevensstructuren", 3),
					entry("Systeemontwerp", 3),
					entry("Algoritmen", 6),
					entry("Gevorderde algoritmen", 6),
					entry("Fysica", 6),
					entry("Informatica", 6),
					entry("Elektronica", 3),
					entry("Mechanica", 6),
					entry("Materialen", 3),
					entry("Ontwerptools", 4)
				);
			
			Map<String, Integer> burg_ing = Map.ofEntries(
					entry("Basiswiskunde", 6),
					entry("Natuurkunde 1", 6),
					entry("Wiskundige Analyse 1", 6),
					entry("Wiskundige Analyse 2", 6),
					entry("Wiskundige Analyse 3", 6),
					entry("Informatica", 6),
					entry("Meetkunde en lineaire algebra", 7),
					entry("Duurzaamheid", 3),
					entry("Discrete Wiskunde", 4),
					entry("Scheikundige thermodynamica", 3)
				);
			
			Map<String, Integer> fysica = Map.ofEntries(
					entry("Programmeren", 6),
					entry("Mechanica", 6),
					entry("Wiskundige structuren en functies", 5),
					entry("Lineaire algebra", 4),
					entry("Chemie", 5),
					entry("Sterren en planeten", 6),
					entry("Elektriciteit en magnetisme", 5),
					entry("Golven en optica", 5),
					entry("Vectoranalyse", 6),
					entry("Theoretische mechanica", 6)
				);
			
			Map<String, Integer> wiskunde = Map.ofEntries(
					entry("Lineaire algebra en meetkunde 1", 6),
					entry("Analyse 1", 6),
					entry("Discrete wiskunde 1", 6),
					entry("Programmeren", 6),
					entry("Computerproject wiskunde", 4),
					entry("Lineaire algebra en meetkunde 2", 6),
					entry("Analyse 2", 8),
					entry("Discrete wiskunde 2", 6),
					entry("Inleiding tot de theoretische fysica", 6),
					entry("Algemene natuurkunde", 6)
				);
			
			Map<String, Integer> dierg = Map.ofEntries(
					entry("Studie van de vertebraten en algemene anatomie van de huisdieren", 12),
					entry("Anorganische chemie", 5),
					entry("Ethologie, dierenethiek en rassenleer", 6),
					entry("Statistiek: analyse", 6),
					entry("Medische fysica en radioprotectie", 7),
					entry("Bio-organische chemie", 7),
					entry("Celbiologie en algemene weefselleer", 7)
				);
			
			Random rnd = new Random();
			
			for (Map.Entry<String, Integer> item : ind_ing.entrySet()) {
				// Make sure to use the addCourse method so that domain events are created
				// to update other services that are interested.
				service.addCourseToFaculty("Ingenieurswetenschappen & architectuur", "Industrieel Ingenieur", item.getKey(), item.getValue(), rnd.nextInt(8) + 1);
			}
			
			for (Map.Entry<String, Integer> item : burg_ing.entrySet()) {
				// Make sure to use the addCourse method so that domain events are created
				// to update other services that are interested.
				
				service.addCourseToFaculty("Ingenieurswetenschappen & architectuur", "Burgerlijk Ingenieur", item.getKey(), item.getValue(), rnd.nextInt(8) + 1);
			}
			
			for (Map.Entry<String, Integer> item : fysica.entrySet()) {
				// Make sure to use the addCourse method so that domain events are created
				// to update other services that are interested.
				service.addCourseToFaculty("Wetenschappen", "Fysica & Sterrenkunde", item.getKey(), item.getValue(), rnd.nextInt(8) + 1);
			}
			
			for (Map.Entry<String, Integer> item : wiskunde.entrySet()) {
				// Make sure to use the addCourse method so that domain events are created
				// to update other services that are interested.
				service.addCourseToFaculty("Wetenschappen", "Wiskunde", item.getKey(), item.getValue(), rnd.nextInt(8) + 1);
			}
			
			for (Map.Entry<String, Integer> item : dierg.entrySet()) {
				// Make sure to use the addCourse method so that domain events are created
				// to update other services that are interested.
				service.addCourseToFaculty("Dierengeneeskunde", "Dierengeneeskunde", item.getKey(), item.getValue(), rnd.nextInt(8) + 1);
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
				for (DegreeProgramme p : faculty.degrees) {
					
					logger.info(">{}", p.getDegreeName());
					for (Course c : p.getAvailableCourses()) {
						logger.info(">>" + c.getCourseName());
					}
				}
				
			}
		};
	}
	
	/*
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
	*/
}
