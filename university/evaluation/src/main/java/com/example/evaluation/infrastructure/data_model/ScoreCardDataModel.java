package com.example.evaluation.infrastructure.data_model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreCardDataModel {
	@Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	String scoreCardId;
	String studentId;
	String degreeId;
	
	@ElementCollection
	@CollectionTable(name = "course_score_data_model", joinColumns = @JoinColumn(name = "score_card_id"))
	List<CourseScoreDataModel> scores;
}
