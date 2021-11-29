package be.ugent.systemdesign.university.curriculum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.systemdesign.university.curriculum.API.messaging.Channels;
import be.ugent.systemdesign.university.curriculum.application.CurriculumService;
import be.ugent.systemdesign.university.curriculum.application.Response;
import be.ugent.systemdesign.university.curriculum.domain.Curriculum;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumRepository;
import be.ugent.systemdesign.university.curriculum.infrastructure.CourseDataModel;
import be.ugent.systemdesign.university.curriculum.infrastructure.CurriculumDataModel;
import be.ugent.systemdesign.university.curriculum.infrastructure.CurriculumDataModelRepository;

@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class CurriculumApplication {
	
	private static final Logger log = LoggerFactory.getLogger(CurriculumApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CurriculumApplication.class, args);
	}
	
	private static void logCurriculumDataModels(List<CurriculumDataModel> curricula) {
		log.info("-Number of curricula found: {}", curricula.size());
		for(CurriculumDataModel curriculum : curricula) { 
			
			StringBuilder sb = new StringBuilder();
			for (CourseDataModel cdm : curriculum.getCourses()) {
				sb.append(cdm.getName());
				sb.append(" (");
				sb.append(cdm.getCredits());
				sb.append(") ");
			}
			
			log.info("--curriculumId {};" +
						" studentId {}; curriculumStatus {}, academicYear {}, dateCreated {}," +
				" dateLastChanged {}, courses {}.",
				curriculum.getCurriculumId(), curriculum.getStudentId(), curriculum.getCurriculumStatus(),
				curriculum.getAcademicYear(), curriculum.getDateCreated(), curriculum.getDateLastChanged(),
				sb.toString()
			); 
		}
	}
	
	@Bean
	public CommandLineRunner populateDatabase(CurriculumDataModelRepository DMrepo, CurriculumRepository repo) {
		return (args) -> {
			DMrepo.deleteAll();
			
			Curriculum c = new Curriculum("1");
			
			c.addCourse("Gevorderde algoritmen", 1);
			c.addCourse("Wiskunde 1", 10);
			c.addCourse("Signale & systemen", 5);
			
			repo.save(c);
			
			//log.info("Saved new inpatient {}", cId);
			log.info("Saved curriculum");
		};
	}
	
	 @Bean CommandLineRunner
	 testCurriculumDataModelRepository(CurriculumDataModelRepository repo) {
		 return (args) -> {
			 log.info("$Testing CurriculumDataModelRepository.");
	  
			  log.info(">Find all curricula from database."); 
			  List<CurriculumDataModel> curriculumAll = repo.findAll(); 
			  logCurriculumDataModels(curriculumAll);
			  
			  log.info(">Find by studentId.");
			  repo.findByStudentId("1").ifPresentOrElse((value) -> {
					  logCurriculumDataModels(Arrays.asList(value));
				  }, () -> {
					  logCurriculumDataModels(Collections.emptyList());
				  }
			  );
		 };
	 }
	
	@Bean
	public CommandLineRunner markAsProposed(CurriculumDataModelRepository DMrepo, CurriculumRepository repo) {
		return (args) -> {
			
		};
	}
	
	 private static void logResponse(Response response) {
		  log.info("-response status[{}] message[{}]", response.status,
		  response.message); }
		
	  @Bean 
	  CommandLineRunner testInpatientHasLeftEvent(CurriculumService service) {
		  return (args) -> { 
			  //Response response = service.noteLeftAgainstMedicalAdvice("3"); logResponse(response);
		  }; 
	  }

}
