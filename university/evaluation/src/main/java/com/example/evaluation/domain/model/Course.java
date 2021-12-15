package com.example.evaluation.domain.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

	@Id
	private String courseId;
	private String courseName;
	private String teacherId;
	private String degreeId;

	@ElementCollection
	@CollectionTable(name = "course_students", joinColumns = @JoinColumn(name = "course_id"))
	@Column(name = "studentId")
	private List<String> studentIds;

}
