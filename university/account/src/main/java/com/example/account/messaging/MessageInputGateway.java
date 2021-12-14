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
	public AccountCreatedResponse receiveCreateAccountRequest(CreateAccountRequest req) {
		String username = (req.getFirstname() + req.getName()).toLowerCase();
		String email = username + "@ugent.be";
		Account account = new Account(null, req.getFirstname(), req.getName(), username, req.getEmail(), email, req.getDateOfBirth(), req.getSocialSecurityNumber(), AccountType.STUDENT, new ArrayList<>());
		account = accountRepo.save(account);
		
		return new AccountCreatedResponse(account.getAccountId(), username, email);
	}
	
	@StreamListener(Channels.PLAGIARISM_DETECTED_EVENT)
	public void registerPlagiarismViolation(PlagiarismEvent event) {
		Account account = accountRepo.getById(event.getStudentId());
		account.getComments().add("Plagiarized task " + event.getTaskId());
		accountRepo.save(account);
	}
}
