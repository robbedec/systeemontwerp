package com.example.account.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String CREATE_ACCOUNT_REQUEST = "create_account_request";
	static final String ACCOUNT_CREATED_RESPONSE = "account_created_response";
	static final String PLAGIARISM_DETECTED_EVENT = "plagiarism_detected_event";
	
	@Output(ACCOUNT_CREATED_RESPONSE)
	MessageChannel accountCreatedResponse();
	
	@Input(CREATE_ACCOUNT_REQUEST)
	SubscribableChannel createAccountRequest();
	
	@Input(PLAGIARISM_DETECTED_EVENT)
	SubscribableChannel registerPlagiarismViolation();
}
