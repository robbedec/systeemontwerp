package be.ugent.systemdesign.university.faculty.infrastructure;

import static java.util.Map.entry;


import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.university.faculty.FacultyApplication;
import be.ugent.systemdesign.university.faculty.application.FacultyService;

@Component
public class ReseedDatabase {
	Logger logger = LoggerFactory.getLogger(FacultyApplication.class);
	
	@Autowired
	FacultyService service;

	
	public void reseedDatabase() {
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
	}
}
