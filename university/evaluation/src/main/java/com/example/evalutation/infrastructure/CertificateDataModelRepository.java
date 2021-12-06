package com.example.evalutation.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateDataModelRepository extends JpaRepository<CertificateDataModel, String> {
	List<CertificateDataModel> findByCourseId(String courseId);
	List<CertificateDataModel> findByStudentId(String studentId);
}
