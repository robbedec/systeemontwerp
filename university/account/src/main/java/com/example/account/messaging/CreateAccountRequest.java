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
	private String firstName;
	private String lastName;
	private String password;
	private String address;
	private LocalDate dateOfBirth;
	private AccountType type;
}
