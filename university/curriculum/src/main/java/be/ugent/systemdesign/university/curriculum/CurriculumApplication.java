package be.ugent.systemdesign.university.curriculum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.systemdesign.university.curriculum.domain.Curriculum;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumRepository;
import be.ugent.systemdesign.university.curriculum.infrastructure.CurriculumDataModelRepository;

@SpringBootApplication
public class CurriculumApplication {
	
	private static final Logger log = LoggerFactory.getLogger(CurriculumApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CurriculumApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner populateDatabase(CurriculumDataModelRepository DMrepo, CurriculumRepository repo) {
		return (args) -> {
			DMrepo.deleteAll();
			
			Curriculum c = new Curriculum();
			
			c.addCourse("Gevorderde algoritmen", 1);
			c.addCourse("Wiskunde 1", 10);
			c.addCourse("Signale & systemen", 5);
			
			String cId = repo.save(c);
			
			log.info("Saved new inpatient {}", cId);
		};
	}

}
