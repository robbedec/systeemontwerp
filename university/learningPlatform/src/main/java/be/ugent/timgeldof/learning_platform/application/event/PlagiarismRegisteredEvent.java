package be.ugent.timgeldof.learning_platform.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlagiarismRegisteredEvent {
	public String studentId;
	public String changeType;
}
