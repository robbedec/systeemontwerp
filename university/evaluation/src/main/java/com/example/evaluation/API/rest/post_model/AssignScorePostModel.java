package com.example.evaluation.API.rest.post_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignScorePostModel {

	private String studentId;
	private int score;

}
