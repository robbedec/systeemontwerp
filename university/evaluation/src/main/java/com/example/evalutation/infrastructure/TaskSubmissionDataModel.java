package com.example.evalutation.infrastructure;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class TaskSubmissionDataModel {
	private int studentId;
	private String zipFile;
	private LocalDate dateSubmited;
	private int score;
}
