package com.example.evaluation.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaluation.domain.model.Certificate;
import com.example.evaluation.domain.model.ScoreCard;
import com.example.evaluation.domain.repository.CertificateRepository;
import com.example.evaluation.domain.repository.CourseRepository;
import com.example.evaluation.domain.repository.ScoreCardRepository;
import com.example.evaluation.infrastructure.exception.CertificateNotFoundException;

@Transactional
@Service
public class CertificateServiceImpl implements CertificateService {
	@Autowired
	private CertificateRepository certificateRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private ScoreCardRepository scoreCardRepo;

	@Override
	public Response generateCertificate(String degreeId, String studentId) {
		try {
			ScoreCard scoreCard = scoreCardRepo.findByStudentIdAndDegreeId(studentId, degreeId);
			if(scoreCard.getScores().size() != courseRepo.findCoursesInDegree(degreeId).size() ||
					scoreCard.passedAllCourses()) {
				return new Response(ResponseStatus.FAIL, "Student hasn't passed all courses");
			}
					
			Certificate certificate = new Certificate(null, degreeId, studentId);
			certificate = certificateRepo.save(certificate);
			return new Response(ResponseStatus.SUCCESS, "ID " + certificate.getCertificateId());
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Persistence failed ");
		}
	}
}
