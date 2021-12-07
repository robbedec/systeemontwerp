package com.example.evaluation.domain;

import java.util.List;

public interface CertificateRepository {
	Certificate findById(String certificateId);
	Certificate save(Certificate certificate);
	List<Certificate> findByStudentId(String studentId);
	List<Certificate> findByDegreeId(String degreeId);
}
