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
	String scoreCardId;
	String studentId;
	String degreeId;
	List<CourseScore> scores;
}