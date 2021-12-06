package com.example.evaluation.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.evaluation.domain.Certificate;
import com.example.evaluation.domain.CertificateRepository;

@Repository
public class CertificateRepositoryImpl implements CertificateRepository {
	@Autowired
	private CertificateDataModelRepository certificateDMRepo;
	
	private Certificate mapToCertificate(CertificateDataModel certificateDM) {
		return new Certificate(certificateDM.getCertificateId(), certificateDM.getCourseId(), certificateDM.getStudentId());
	}
	
	private CertificateDataModel mapToCertificateDataModel(Certificate certificate) {
		return new CertificateDataModel(certificate.getCertificateId(), certificate.getCourseId(), certificate.getCertificateId());
	}

	@Override
	public Certificate findById(String certificateId) {
		CertificateDataModel certificateDM = certificateDMRepo.findById(certificateId).orElseThrow(CertificateNotFoundException::new);
		return mapToCertificate(certificateDM);
	}

	@Override
	public void save(Certificate certificate) {
		certificateDMRepo.save(mapToCertificateDataModel(certificate));
	}

	@Override
	public List<Certificate> findByStudentId(String studentId) {
		return certificateDMRepo.findByStudentId(studentId).stream()
				.map(certificateDM -> mapToCertificate(certificateDM)).collect(Collectors.toList());
	}

	@Override
	public List<Certificate> findByCourseId(String courseId) {
		return certificateDMRepo.findByCourseId(courseId).stream()
				.map(certificateDM -> mapToCertificate(certificateDM)).collect(Collectors.toList());
	}

}
