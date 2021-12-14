package com.example.evaluation.infrastructure.query;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evaluation.application.query.ScoreCardQuery;
import com.example.evaluation.application.query.read_model.CourseScoreReadModel;
import com.example.evaluation.application.query.read_model.ScoreCardReadModel;
import com.example.evaluation.infrastructure.data_model.ScoreCardDataModel;
import com.example.evaluation.infrastructure.exception.ScoreCardNotFoundException;
import com.example.evaluation.infrastructure.repository.ScoreCardDataModelRepository;

@Component
public class ScoreCardQueryImpl implements ScoreCardQuery {
	Logger log = LoggerFactory.getLogger(ScoreCardQueryImpl.class);
	
	@Autowired
	private ScoreCardDataModelRepository scoreCardDMRepo;

	private ScoreCardReadModel mapToScoreCardReadModel(ScoreCardDataModel scoreCardDM) {
		return new ScoreCardReadModel(scoreCardDM.getDegreeId(), scoreCardDM.getScores().stream()
				.map(courseScoreDM -> new CourseScoreReadModel(courseScoreDM.getCourseId(), courseScoreDM.getScore()))
				.collect(Collectors.toList()));
	}

	@Override
	public List<ScoreCardReadModel> getScoreCards(String studentId) {
		List<ScoreCardDataModel> scorecards = scoreCardDMRepo.findByStudentId(studentId);
		for(ScoreCardDataModel sc : scorecards) {
			log.info("sc {} {}", sc.getDegreeId(), sc.getStudentId());
		}
		return scoreCardDMRepo.findByStudentId(studentId).stream()
				.map(scoreCardDM -> mapToScoreCardReadModel(scoreCardDM)).collect(Collectors.toList());
	}

}
