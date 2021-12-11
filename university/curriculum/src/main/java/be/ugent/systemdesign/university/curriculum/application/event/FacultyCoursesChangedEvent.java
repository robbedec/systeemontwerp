package be.ugent.systemdesign.university.curriculum.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacultyCoursesChangedEvent {
	
	private Integer courseId;
	private String changeType;
	private String facultyName;
	private String degreeName;
	private String courseName;
	private Integer courseCredits;
	private Integer teacherId;
}
