package com.example.evaluation.API.rest.view_model;

import java.util.List;
import java.util.stream.Collectors;

import com.example.evaluation.application.query.read_model.ScoreCardReadModel;

public class ScoreCardViewModel {
	public final String degreeId;
	public final List<CourseScoreViewModel> scores;

	public ScoreCardViewModel(ScoreCardReadModel scoreCardRM) {
		degreeId = scoreCardRM.degreeId;
		scores = scoreCardRM.scores.stream().map(courseScoreRM -> new CourseScoreViewModel(courseScoreRM)).collect(Collectors.toList());
	}
}
