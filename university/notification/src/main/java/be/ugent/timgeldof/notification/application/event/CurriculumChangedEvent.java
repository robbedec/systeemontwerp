package be.ugent.timgeldof.notification.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurriculumChangedEvent {
	private String studentId;
	private String courseName;
	private String courseCredits;
	private String changeType;
}
