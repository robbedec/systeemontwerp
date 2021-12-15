package com.example.evaluation.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurriculumChangedEvent {

	private String studentId;
	private String courseId;
	private String courseName;
	private int courseCredits;
	private String changeType;

}
