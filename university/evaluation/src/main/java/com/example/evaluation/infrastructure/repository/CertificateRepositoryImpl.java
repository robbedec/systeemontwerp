package com.example.evaluation.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.evaluation.domain.model.Certificate;
import com.example.evaluation.domain.repository.CertificateRepository;
import com.example.evaluation.infrastructure.data_model.CertificateDataModel;
import com.example.evaluation.infrastructure.exception.CertificateNotFoundException;

@Repository
public class CertificateRepositoryImpl implements CertificateRepository {
	@Autowired
	private CertificateDataModelRepository certificateDMRepo;

	@Override
	public Certificate findById(String certificateId) {
		CertificateDataModel certificateDM = certificateDMRepo.findById(certificateId)
				.orElseThrow(CertificateNotFoundException::new);
		return mapToCertificate(certificateDM);
	}

	@Override
	public Certificate save(Certificate certificate) {
		CertificateDataModel certificateDM = certificateDMRepo.save(mapToCertificateDataModel(certificate));
		return mapToCertificate(certificateDM);
	}

	@Override
	public List<Certificate> findByStudentId(String studentId) {
		return certificateDMRepo.findByStudentId(studentId).stream()
				.map(certificateDM -> mapToCertificate(certificateDM)).collect(Collectors.toList());
	}

	@Override
	public List<Certificate> findByDegreeId(String degreeId) {
		return certificateDMRepo.findByDegreeId(degreeId).stream().map(certificateDM -> mapToCertificate(certificateDM))
				.collect(Collectors.toList());
	}
	
	// Mappings from domain model <-> data model
	private Certificate mapToCertificate(CertificateDataModel certificateDM) {
		return new Certificate(certificateDM.getCertificateId(), certificateDM.getDegreeId(),
				certificateDM.getStudentId());
	}

	private CertificateDataModel mapToCertificateDataModel(Certificate certificate) {
		return new CertificateDataModel(certificate.getCertificateId(), certificate.getDegreeId(),
				certificate.getStudentId());
	}

}
