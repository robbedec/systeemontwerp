package com.example.evaluation.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacultyEvent {
	private String courseId;
	private String changeType;
	private String facultyName;
	private String degreeId;
	private String degreeName;
	private String courseName;
	private int courseCredits;
	private String teacherId;
}
