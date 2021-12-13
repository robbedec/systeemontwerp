package com.example.account.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountCreatedResponse {
	private String accountId;
	private String username;
	private String email;
}
