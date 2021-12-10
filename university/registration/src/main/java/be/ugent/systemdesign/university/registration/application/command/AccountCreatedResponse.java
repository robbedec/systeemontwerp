package be.ugent.systemdesign.university.registration.application.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import be.ugent.systemdesign.university.registration.application.Response;
import be.ugent.systemdesign.university.registration.application.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountCreatedResponse extends Response {

	private String registrationId;
	private String accountId;
	
	public AccountCreatedResponse(ResponseStatus status, String message, String accountId) {
		super(status, message);
		this.accountId = accountId;
	}
}
