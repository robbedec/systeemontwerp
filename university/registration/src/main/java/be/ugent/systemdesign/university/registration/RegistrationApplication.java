package be.ugent.systemdesign.university.registration;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.systemdesign.university.registration.API.messaging.Channels;
import be.ugent.systemdesign.university.registration.application.RegistrationService;
import be.ugent.systemdesign.university.registration.application.Response;
import be.ugent.systemdesign.university.registration.application.query.RegistrationQuery;
import be.ugent.systemdesign.university.registration.application.query.RegistrationReadModel;
import be.ugent.systemdesign.university.registration.domain.Registration;
import be.ugent.systemdesign.university.registration.domain.RegistrationRepository;
import be.ugent.systemdesign.university.registration.infrastructure.RegistrationDataModelRepository;
import be.ugent.systemdesign.university.registration.infrastructure.RegistrationNotFoundException;

@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class RegistrationApplication {
	
	private static Logger logger = LoggerFactory.getLogger(RegistrationApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner populateDatabase(RegistrationDataModelRepository DMrepo, RegistrationRepository repo) {
		return (args) -> {
			DMrepo.deleteAll();
			
			Registration r = new Registration(new Date(), "bramdb@gmail.com", "De Bleecker", "Bram", LocalDate.of(1999, 12, 24), "Ingenieurswetenschappen & architectuur", "Industrieel Ingenieur");
			Registration r2 = new Registration(new Date(), "bob@gmail.com", "De Bleecker", "BOB", LocalDate.of(1720, 10, 2), "Ingenieurswetenschappen & architectuur", "Industrieel Ingenieur");
			repo.save(r);
			repo.save(r2);
			logger.info("Database populated");
		};
	}
	
	/**@Bean
	CommandLineRunner testRepository(RegistrationDataModelRepository repo) {
		return (args) -> {
			var x = repo.findById(1).get();
			logger.info(x.getFirstName());
			logger.info(x.getName());
			logger.info(x.getEmail());
			logger.info(x.getCourse());
			logger.info(x.getPayementStatus());
			logger.info(x.getDateOfBirth().toString());
			logger.info(x.getRegistrationDate().toString());			
		};
	}**/
	
	/**@Bean
	CommandLineRunner testRepository(RegistrationRepository repo) {
		return (args) -> {			
			try {
				var x = repo.findOne(1);
				logger.info(x.getFirstName());			
				var y = repo.findOne(2);
			} catch(RegistrationNotFoundException ex) {
				logger.info("Exception succesfully thrown");
			}
		};
	}**/
	
	private static void logResponse(Response response) {
		  logger.info("-response status[{}] message[{}]", response.getStatus(),
		  response.getMessage()); 
  }
	
	@Bean
	CommandLineRunner testRepository(RegistrationService service, RegistrationQuery query) {
		return (args) -> {			
			logger.info("$Testing RegistrationService."); 
			Response response;
			logger.info("Register new registration (success).");
			response = service.addRegistration("new@mail.be", "De Bleecker", "Sam", "2001-01-01", "Geneeskunde", "Biomedische Wetenschappen");			
			logResponse(response);
			response = service.addRegistration("new@mail.be", "Cool", "Bobby", "2001-01-01", "Architectuur", "Architect");
			logResponse(response);		
			List<RegistrationReadModel> list = query.giveRegistrations("SUBMITTED");
			for(var x : list) {
				logger.info("Registration:");
				logger.info("   - id: "+x.getRegistrationId());
				logger.info("   - mail: "+x.getEmail());
				logger.info("   - firstname: "+x.getFirstName());
				logger.info("   - lastname: "+x.getName());
				logger.info("   - date of birth: "+x.getDateOfBirth());
				logger.info("   - faculty: "+x.getFaculty());
				logger.info("   - degree: "+x.getDegree());
				logger.info("-----------------------");
			}
			response = service.acceptRegistration("1");
			logResponse(response);
			
			
			List<RegistrationReadModel> regs = query.giveRegistrations("1");
			logger.info(Integer.toString(regs.size()));
			
		};
	}
}
