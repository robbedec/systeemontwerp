package be.ugent.systemdesign.university.invoice.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewRegistrationEvent {
	private String accountId;
	private String email;
	private String faculty;
	private String degree;
}
