package be.ugent.timgeldof.learning_platform.domain.course_access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumChangedDomainEvent {
	private String studentId;
	private String courseName;
	private String courseCredits;
	private String changeType;
}
