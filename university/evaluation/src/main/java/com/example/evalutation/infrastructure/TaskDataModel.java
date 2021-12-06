package com.example.evalutation.infrastructure;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TaskDataModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskId;
	private int courseId;
	private String description;
	private LocalDate dueDate;
	
	@ElementCollection
	@CollectionTable(name="task_submission_data_model", joinColumns=@JoinColumn(name="task_id"))
	private List<TaskSubmissionDataModel> submissions;
}
