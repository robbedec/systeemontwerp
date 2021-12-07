package com.example.evaluation.infrastructure.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evaluation.application.query.CertificateQuery;
import com.example.evaluation.application.query.CertificateVerificationReadModel;
import com.example.evaluation.infrastructure.exception.CertificateNotFoundException;
import com.example.evaluation.infrastructure.repository.CertificateDataModelRepository;

@Component
public class CertificateQueryImpl implements CertificateQuery{
	
	@Autowired
	CertificateDataModelRepository certifcateDMRepo;

	@Override
	public CertificateVerificationReadModel verifyCertificate(String certificateId) {
		try {
			certifcateDMRepo.findById(certificateId);
			return new CertificateVerificationReadModel(true);
		} catch (CertificateNotFoundException e) {
			return new CertificateVerificationReadModel(false);
		}
	}
	
}
