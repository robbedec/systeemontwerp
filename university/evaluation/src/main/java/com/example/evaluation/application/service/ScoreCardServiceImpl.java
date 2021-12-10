package com.example.evaluation.application.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaluation.domain.model.Task;
import com.example.evaluation.domain.model.TaskSubmission;
import com.example.evaluation.domain.repository.ScoreCardRepository;
import com.example.evaluation.domain.repository.TaskRepository;
import com.example.evaluation.domain.repository.TaskSubmissionRepository;
import com.example.evaluation.infrastructure.exception.TaskSubmissionNotFoundException;
import com.example.evaluation.domain.model.CourseScore;
import com.example.evaluation.domain.model.ScoreCard;

@Transactional
@Service
public class ScoreCardServiceImpl implements ScoreCardService {
	@Autowired
	ScoreCardRepository scoreCardRepo;

	@Autowired
	TaskRepository taskRepo;

	@Autowired
	TaskSubmissionRepository taskSubmissionRepo;

	@Override
	public Response generateScoreCards(String degreeId) {
		// TODO for(students that follow degree)
		{
			String studentId = "";
			generateScoreCard(studentId, degreeId);
		}
		return new Response(ResponseStatus.SUCCESS, "Score cards generated");
	}

	private void generateScoreCard(String studentId, String degreeId) {
		ScoreCard scoreCard = new ScoreCard(null, studentId, degreeId, new ArrayList<>());
		// TODO for(courses that student follows)
		{
			String courseId = "";
			double score = 0;
			for (Task task : taskRepo.findByCourseId(courseId)) {
				try {
					TaskSubmission taskSubmission = taskSubmissionRepo.findByTaskIdAndStudentId(courseId, studentId);
					score += taskSubmission.getScore() * task.getWeight();
				} catch (TaskSubmissionNotFoundException e) {
				}
			}
			scoreCard.getScores().add(new CourseScore(courseId, (int) Math.round(score)));
		}
		scoreCardRepo.save(scoreCard);
	}
}
