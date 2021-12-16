package be.ugent.systemdesign.university.curriculum.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewRegistrationEvent {
	
	@JsonProperty("accountId")
	private String studentNumber;
	
	@JsonProperty("faculty")
	private String facultyName;
	
	@JsonProperty("degree")
	private String degreeName;
}
