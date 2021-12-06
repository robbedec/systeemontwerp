package com.example.evalutation.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateDataModelRepository extends JpaRepository<CertificateDataModel, Integer> {
	List<CertificateDataModel> findByCourseId(int courseId);
	List<CertificateDataModel> findByStudentId(int studentId);
}
