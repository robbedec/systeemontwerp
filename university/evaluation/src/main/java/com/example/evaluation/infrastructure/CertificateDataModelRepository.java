package com.example.evaluation.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateDataModelRepository extends JpaRepository<CertificateDataModel, String> {
	List<CertificateDataModel> findByCourseId(String courseId);
	List<CertificateDataModel> findByStudentId(String studentId);
}
