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
	private String courseId;
	private String studentId;
	private String changeType;
}
