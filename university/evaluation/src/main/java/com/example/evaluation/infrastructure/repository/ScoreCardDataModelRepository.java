package com.example.evaluation.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evaluation.infrastructure.data_model.ScoreCardDataModel;

@Repository
public interface ScoreCardDataModelRepository extends JpaRepository<ScoreCardDataModel, String>{
	Optional<ScoreCardDataModel> findByStudentIdAndDegreeId(String studentId, String degreeId);
}
