package com.example.evaluation.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateDataModelRepository extends JpaRepository<CertificateDataModel, String> {
	List<CertificateDataModel> findByDegreeId(String degreeId);
	List<CertificateDataModel> findByStudentId(String studentId);
}
