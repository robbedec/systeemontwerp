package com.example.evaluation.domain.repository;

import java.util.List;

import com.example.evaluation.domain.model.Certificate;

public interface CertificateRepository {

	Certificate findById(String certificateId);

	Certificate save(Certificate certificate);

	List<Certificate> findByStudentId(String studentId);

	List<Certificate> findByDegreeId(String degreeId);

}
