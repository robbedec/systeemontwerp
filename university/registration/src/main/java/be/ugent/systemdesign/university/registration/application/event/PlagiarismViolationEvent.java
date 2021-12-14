package be.ugent.systemdesign.university.registration.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlagiarismViolationEvent {
	private String taskId;
	private String studentId;
}
