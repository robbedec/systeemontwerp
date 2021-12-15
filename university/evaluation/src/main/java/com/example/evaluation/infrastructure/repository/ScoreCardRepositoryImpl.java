package com.example.evaluation.infrastructure.repository;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import com.example.evaluation.domain.model.CourseScore;
import com.example.evaluation.domain.model.ScoreCard;
import com.example.evaluation.domain.repository.ScoreCardRepository;
import com.example.evaluation.infrastructure.data_model.CourseScoreDataModel;
import com.example.evaluation.infrastructure.data_model.ScoreCardDataModel;
import com.example.evaluation.infrastructure.exception.ScoreCardNotFoundException;

@Repository
public class ScoreCardRepositoryImpl implements ScoreCardRepository {

	@Autowired
	ScoreCardDataModelRepository scoreCardDMRepo;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Override
	public ScoreCard findByStudentIdAndDegreeId(String studentId, String degreeId) {
		ScoreCardDataModel scoreCardDM = scoreCardDMRepo.findByStudentIdAndDegreeId(studentId, degreeId)
				.orElseThrow(ScoreCardNotFoundException::new);
		return mapToScoreCard(scoreCardDM);
	}

	@Override
	public ScoreCard save(ScoreCard scoreCard) {
		ScoreCardDataModel scoreCardDM = scoreCardDMRepo.save(mapToScoreCardDataModel(scoreCard));

		scoreCard.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
		scoreCard.clearDomainEvents();

		return mapToScoreCard(scoreCardDM);
	}

	// Mappings from domain model <-> data model
	private ScoreCard mapToScoreCard(ScoreCardDataModel scoreCardDM) {
		return new ScoreCard(scoreCardDM.getScoreCardId(), scoreCardDM.getStudentId(), scoreCardDM.getDegreeId(),
				scoreCardDM.getScores().stream().map(courseScoreDM -> mapToCourseScore(courseScoreDM))
						.collect(Collectors.toList()));
	}

	private CourseScore mapToCourseScore(CourseScoreDataModel courseScoreDM) {
		return new CourseScore(courseScoreDM.getCourseId(), courseScoreDM.getScore());
	}

	private ScoreCardDataModel mapToScoreCardDataModel(ScoreCard scoreCard) {
		return new ScoreCardDataModel(scoreCard.getScoreCardId(), scoreCard.getStudentId(), scoreCard.getDegreeId(),
				scoreCard.getScores().stream().map(courseScore -> mapToCourseScoreDataModel(courseScore))
						.collect(Collectors.toList()));
	}

	private CourseScoreDataModel mapToCourseScoreDataModel(CourseScore courseScore) {
		return new CourseScoreDataModel(courseScore.getCourseId(), courseScore.getScore());
	}

}
