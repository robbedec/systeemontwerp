package com.example.evaluation.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evaluation.infrastructure.data_model.ScoreCardDataModel;

@Repository
public interface ScoreCardDataModelRepository extends JpaRepository<ScoreCardDataModel, String> {

	List<ScoreCardDataModel> findByStudentId(String studentId);

	Optional<ScoreCardDataModel> findByStudentIdAndDegreeId(String studentId, String degreeId);

}
