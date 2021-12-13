package be.ugent.systemdesign.university.curriculum.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisenrollmentEvent {

	public String studentId;
}
