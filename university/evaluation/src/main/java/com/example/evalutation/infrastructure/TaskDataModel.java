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
public class TaskDataModel {
	@Id
	private String taskId;
	private String courseId;
	private String description;
	private LocalDate dueDate;
}
