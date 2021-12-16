package com.example.evaluation.API.rest.view_model;

import com.example.evaluation.application.query.read_model.CourseScoreReadModel;

public class CourseScoreViewModel {

	public final String course;
	public final String score;

	public CourseScoreViewModel(CourseScoreReadModel courseScoreRM) {
		course = courseScoreRM.course;
		score = courseScoreRM.score + "/20";
	}

}
