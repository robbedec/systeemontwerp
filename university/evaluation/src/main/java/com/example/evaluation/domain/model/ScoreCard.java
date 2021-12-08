package com.example.evaluation.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreCard {
	private String scoreCardId;
	private String studentId;
	private String degreeId;
	private List<CourseScore> scores;
}