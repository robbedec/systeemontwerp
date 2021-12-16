package com.example.account.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AccountCreatedResponse extends Response{
	private String registrationId;
	private String accountId;
	private String username;
	private String email;
	
	public AccountCreatedResponse(ResponseStatus status, String message, String registrationId, String accountId, String username, String email) {
		super(status, message);
		this.registrationId = registrationId;
		this.accountId = accountId;
		this.username = username;
		this.email = email;
	}
}
