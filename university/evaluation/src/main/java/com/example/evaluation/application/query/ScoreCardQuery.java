package com.example.evaluation.application.query;

public interface ScoreCardQuery {
	ScoreCardReadModel getScoreCard(String studentId, String degreeId);
}
