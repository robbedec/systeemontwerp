package com.example.evaluation.domain.repository;

import com.example.evaluation.domain.model.ScoreCard;

public interface ScoreCardRepository {

	ScoreCard save(ScoreCard scoreCard);

	ScoreCard findByStudentIdAndDegreeId(String studentId, String degreeId);

}
