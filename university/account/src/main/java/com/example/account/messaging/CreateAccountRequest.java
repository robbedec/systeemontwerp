package com.example.account.messaging;

import java.time.LocalDate;

import com.example.account.data.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountRequest {
	private String registrationId;
	private String email;
	private String name;
	private String firstname;
	private LocalDate dateOfBirth;
}
