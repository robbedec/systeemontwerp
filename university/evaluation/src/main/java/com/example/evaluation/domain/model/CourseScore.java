package com.example.evaluation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseScore {

	private String course;
	private int score;

	public boolean passed() {
		return score >= 10;
	}

}
