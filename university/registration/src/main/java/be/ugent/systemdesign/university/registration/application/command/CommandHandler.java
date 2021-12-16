package be.ugent.systemdesign.university.registration.application.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.systemdesign.university.registration.application.ResponseStatus;
import be.ugent.systemdesign.university.registration.domain.Registration;
import be.ugent.systemdesign.university.registration.domain.RegistrationRepository;

@Service
public class CommandHandler {
	
	private static final Logger log = LoggerFactory.getLogger(CommandHandler.class);
	
	@Autowired
	RegistrationRepository repo;
	
	public void handleAccountCreatedResponse(AccountCreatedResponse response) {
		log.info("status {}, message {}, registrationId {}, accountId {}", response.getStatus(), response.getMessage(), response.getRegistrationId(), response.getAccountId(), response.getRegistrationId());
		try {
			Registration r = repo.findOne(Integer.parseInt(response.getRegistrationId()));
			r.setAccountId(response.getAccountId());
			repo.save(r);			
			if(response.getStatus() == ResponseStatus.SUCCESS) {				
				r.accept();			
				repo.save(r);
				log.info("Registration has been updated");
				log.info("AccountId: " + response.getAccountId());
			}
		} catch (RuntimeException ex) {
			log.info("Failed handling the accountCreatedResponse");
		}
	}
	
}
