package be.ugent.systemdesign.university.registration;

import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.systemdesign.university.registration.application.RegistrationService;
import be.ugent.systemdesign.university.registration.application.Response;
import be.ugent.systemdesign.university.registration.domain.Registration;
import be.ugent.systemdesign.university.registration.domain.RegistrationRepository;
import be.ugent.systemdesign.university.registration.infrastructure.RegistrationDataModelRepository;
import be.ugent.systemdesign.university.registration.infrastructure.RegistrationNotFoundException;

@SpringBootApplication
public class RegistrationApplication {
	
	private static Logger logger = LoggerFactory.getLogger(RegistrationApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
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
		  logger.info("-response status[{}] message[{}]", response.status,
		  response.message); 
  }
	
	@Bean
	CommandLineRunner testRepository(RegistrationService service) {
		return (args) -> {			
			logger.info("$Testing RegistrationService."); 
			Response response;
			logger.info("Register new registration (success).");
			response = service.register(new Date(), "new@mail.be", "Brum", "DuBleeckur", LocalDate.of(2001, 1, 1), "Burgie");
			logResponse(response);
		};
	}
}
