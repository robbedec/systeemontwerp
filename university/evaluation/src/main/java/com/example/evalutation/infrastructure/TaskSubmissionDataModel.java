package com.example.evalutation.infrastructure;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TaskSubmissionDataModel {
	@Id
	private String submissionId;
	private String taskId;
	private String studentId;
	private String file;
	private LocalDate dateSubmited;
	private int score;
}
