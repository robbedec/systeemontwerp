package com.example.evaluation.domain.model;

import java.util.List;

import com.example.evaluation.domain.event.ScoreCardGeneratedDomainEvent;
import com.example.evaluation.domain.seedwork.AggregateRoot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreCard extends AggregateRoot {
	private String scoreCardId;
	private String studentId;
	private String degreeId;
	private List<CourseScore> scores;

	public boolean passedAllCourses() {
		for (CourseScore courseScore : scores) {
			if (!courseScore.passed())
				return false;
		}
		return true;
	}
	
	public void passed(boolean passed) {
		addDomainEvent(new ScoreCardGeneratedDomainEvent(studentId, degreeId, passed));
	}
}