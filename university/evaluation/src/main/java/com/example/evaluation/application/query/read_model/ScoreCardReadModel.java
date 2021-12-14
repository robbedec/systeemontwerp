package com.example.evaluation.application.query.read_model;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ScoreCardReadModel {
	public final String degreeId;
	public final List<CourseScoreReadModel> scores;
}
