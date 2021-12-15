package com.example.evaluation.API.rest.post_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskPostModel {

	private String courseId;
	private String description;
	private String dueDate;
	private int weight;

}
