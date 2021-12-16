package com.example.account.messaging;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.example.account.data.Account;
import com.example.account.data.AccountRepository;
import com.example.account.data.AccountType;

@Component
public class MessageInputGateway {
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	Channels channels;
	
	@StreamListener(Channels.CREATE_ACCOUNT_REQUEST)
	@SendTo(Channels.ACCOUNT_CREATED_RESPONSE)
	public Response receiveCreateAccountRequest(CreateAccountRequest req) {
		Response response;
		try {
			
		
		String username = (req.getFirstname() + req.getName()).toLowerCase();
		String email = username + "@ugent.be";
		
		Account account = accountRepo.findBySocialSecurityNumber(req.getSocialSecurityNumber()).orElse(new Account(null, req.getFirstname(), req.getName(), username, req.getEmail(), email, req.getDateOfBirth(), req.getSocialSecurityNumber(), AccountType.STUDENT, new ArrayList<>()));
		account = accountRepo.save(account);
		response = new AccountCreatedResponse(ResponseStatus.SUCCESS, "id "+req.getRegistrationId(), req.getRegistrationId(), account.getAccountId(), username, email); 
		 
		} catch (RuntimeException e) {
			response = new AccountCreatedResponse(ResponseStatus.FAIL, "id "+req.getRegistrationId(), null, null, null, null);
		}
		return response;
	}
	
	@StreamListener(Channels.PLAGIARISM_DETECTED_EVENT)
	public void registerPlagiarismViolation(PlagiarismEvent event) {
		try {
			Account account = accountRepo.getById(event.getStudentId());
			account.getComments().add("Plagiarized task " + event.getTaskId());
			accountRepo.save(account);
		} catch( RuntimeException e) {
			
		}
	}
}
