package com.example.evaluation.infrastructure.query;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evaluation.application.query.CourseScoreReadModel;
import com.example.evaluation.application.query.ScoreCardQuery;
import com.example.evaluation.application.query.ScoreCardReadModel;
import com.example.evaluation.infrastructure.data_model.ScoreCardDataModel;
import com.example.evaluation.infrastructure.exception.ScoreCardNotFoundException;
import com.example.evaluation.infrastructure.repository.ScoreCardDataModelRepository;

@Component
public class ScoreCardQueryImpl implements ScoreCardQuery {
	@Autowired
	private ScoreCardDataModelRepository scoreCardDMRepo;

	private ScoreCardReadModel mapToScoreCardReadModel(ScoreCardDataModel scoreCardDM) {
		return new ScoreCardReadModel(scoreCardDM.getDegreeId(), scoreCardDM.getScores().stream()
				.map(courseScoreDM -> new CourseScoreReadModel(courseScoreDM.getCourseId(), courseScoreDM.getScore()))
				.collect(Collectors.toList()));
	}

	@Override
	public ScoreCardReadModel getScoreCard(String studentId, String degreeId) {
		return mapToScoreCardReadModel(scoreCardDMRepo.findByStudentIdAndDegreeId(studentId, degreeId)
				.orElseThrow(ScoreCardNotFoundException::new));
	}

}
