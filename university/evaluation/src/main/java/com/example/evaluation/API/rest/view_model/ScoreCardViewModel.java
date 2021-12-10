package com.example.evaluation.API.rest.view_model;

import com.example.evaluation.application.query.CourseScoreReadModel;
import com.example.evaluation.application.query.ScoreCardReadModel;

public class ScoreCardViewModel {
	public final String degreeId;
	public final String scores;

	public ScoreCardViewModel(ScoreCardReadModel scoreCardRM) {
		degreeId = scoreCardRM.degreeId;
		StringBuilder sb = new StringBuilder();
		for (CourseScoreReadModel csrm : scoreCardRM.scores) {
			sb.append(csrm.courseId).append(": ").append(csrm.score).append("/20\n");
		}
		scores = sb.toString();
	}
}
