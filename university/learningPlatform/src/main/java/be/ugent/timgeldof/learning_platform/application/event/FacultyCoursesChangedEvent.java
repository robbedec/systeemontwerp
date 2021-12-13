package be.ugent.timgeldof.learning_platform.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacultyCoursesChangedEvent {
	// Changetype can be either ADDED or REMOVED
	private String courseId;
	private String changeType;
	private String facultyName;
	private String courseName;
	private Integer courseCredits;
	private String teacherId;
}
