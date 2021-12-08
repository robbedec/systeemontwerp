package be.ugent.timgeldof.notification.application.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewRegistrationEvent {
	public String studentId;
}
