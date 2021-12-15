package com.example.evaluation.application.query;

import java.util.List;

import com.example.evaluation.application.query.read_model.ScoreCardReadModel;

public interface ScoreCardQuery {

	List<ScoreCardReadModel> getScoreCards(String studentId);

}
