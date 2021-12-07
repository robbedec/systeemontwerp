package com.example.evaluation.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaluation.domain.Certificate;
import com.example.evaluation.domain.CertificateRepository;
import com.example.evaluation.infrastructure.CertificateNotFoundException;

@Transactional
@Service
public class CertificateServiceImpl implements CertificateService{
	@Autowired
	private CertificateRepository certificateRepo;

	@Override
	public Response createCertificate(String degreeId, String studentId) {
		// TODO Check if student passed all mandatory courses
		Certificate certificate = new Certificate(null, degreeId, studentId);
		try {
			certificate = certificateRepo.save(certificate);
			return new Response(ResponseStatus.SUCCESS, "ID " + certificate.getCertificateId());
		} catch(Exception e) {
			return new Response(ResponseStatus.FAIL, "Persistence failed ");
		}
	}

	@Override
	public Response verifyCertificate(String certificateId) {
		try {
			certificateRepo.findById(certificateId);
			return new Response(ResponseStatus.SUCCESS, "Valid certificate");
		} catch(CertificateNotFoundException e) {
			return new Response(ResponseStatus.SUCCESS, "Invalid certificate");
		} catch(RuntimeException e) {
			return new Response(ResponseStatus.FAIL, "Unable to verify certificate");
		}
	}
	
}
