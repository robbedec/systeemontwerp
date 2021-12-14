package com.example.evaluation.infrastructure.query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evaluation.application.query.CertificateQuery;
import com.example.evaluation.application.query.read_model.CertificateReadModel;
import com.example.evaluation.application.query.read_model.CertificateVerificationReadModel;
import com.example.evaluation.infrastructure.data_model.CertificateDataModel;
import com.example.evaluation.infrastructure.repository.CertificateDataModelRepository;

@Component
public class CertificateQueryImpl implements CertificateQuery {
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	CertificateDataModelRepository certifcateDMRepo;

	@Override
	public CertificateVerificationReadModel verifyCertificate(String certificateId) {
		Optional<CertificateDataModel> certificate = certifcateDMRepo.findById(certificateId);
		return new CertificateVerificationReadModel(certificate.isPresent());
	}

	@Override
	public List<CertificateReadModel> getCertificates(String studentId) {
		return certifcateDMRepo.findByStudentId(studentId).stream()
				.map(certificateDM -> mapToCertificateReadModel(certificateDM)).collect(Collectors.toList());
	}

	private CertificateReadModel mapToCertificateReadModel(CertificateDataModel certificateDataModel) {
		log.info("{} {}", certificateDataModel.getCertificateId(), certificateDataModel.getDegreeId());
		return new CertificateReadModel(certificateDataModel.getCertificateId(), certificateDataModel.getDegreeId());
	}

}
