package com.example.evaluation.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evaluation.infrastructure.data_model.CertificateDataModel;

@Repository
public interface CertificateDataModelRepository extends JpaRepository<CertificateDataModel, String> {

	List<CertificateDataModel> findByDegreeId(String degreeId);

	List<CertificateDataModel> findByStudentId(String studentId);

}
