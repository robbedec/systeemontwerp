package be.ugent.timgeldof.notification.application.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurriculumChangedEvent {
	private String studentId;
	private String courseName;
	private String courseCredits;
	private String changeType;
}
