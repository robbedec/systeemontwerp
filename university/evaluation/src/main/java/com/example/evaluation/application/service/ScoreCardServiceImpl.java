package com.example.evaluation.application.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaluation.domain.model.Task;
import com.example.evaluation.domain.model.TaskSubmission;
import com.example.evaluation.domain.repository.CertificateRepository;
import com.example.evaluation.domain.repository.CourseRepository;
import com.example.evaluation.domain.repository.ScoreCardRepository;
import com.example.evaluation.domain.repository.TaskRepository;
import com.example.evaluation.infrastructure.exception.TaskSubmissionNotFoundException;
import com.example.evaluation.domain.model.Certificate;
import com.example.evaluation.domain.model.CourseScore;
import com.example.evaluation.domain.model.ScoreCard;

@Transactional
@Service
public class ScoreCardServiceImpl implements ScoreCardService {

	private static final Logger log = LoggerFactory.getLogger(ScoreCardServiceImpl.class);

	@Autowired
	ScoreCardRepository scoreCardRepo;

	@Autowired
	TaskRepository taskRepo;

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	CertificateRepository certificateRepo;

	@Override
	public Response generateScoreCards() {
		for (String studentId : courseRepo.findStudents()) {
			for (String degreeId : courseRepo.findDegreesStudentFollows(studentId)) {
				ScoreCard scoreCard = new ScoreCard(null, studentId, degreeId, new ArrayList<>());

				// Calculate final score
				for (String courseId : courseRepo.findCoursesStudentFollowsInDegree(degreeId, studentId)) {
					double score = 0;
					for (Task task : taskRepo.findByCourseId(courseId)) {
						try {
							TaskSubmission taskSubmission = taskRepo
									.findSubmissionByTaskIdAndStudentId(task.getTaskId(), studentId);
							if (taskSubmission.scoreIsAssigned())
								score += taskSubmission.getScore() * task.getWeight();
						} catch (TaskSubmissionNotFoundException e) {
						}
					}
					scoreCard.getScores()
							.add(new CourseScore(courseRepo.findCourseName(courseId), (int) Math.round(score)));
				}

				// Generate certificate if student passed all courses
				if (scoreCard.getScores().size() == courseRepo.findCoursesInDegree(degreeId).size()
						&& scoreCard.passedAllCourses()) {
					Certificate certificate = new Certificate(null, degreeId, studentId);
					certificate = certificateRepo.save(certificate);
					log.info("Certificate generated for student {} for degree {}", studentId, degreeId);
					scoreCard.passed(true);
				} else {
					scoreCard.passed(false);
				}
				scoreCardRepo.save(scoreCard);
			}
		}
		log.info("Score cards generated");
		return new Response(ResponseStatus.SUCCESS, "Score cards generated");
	}

}
