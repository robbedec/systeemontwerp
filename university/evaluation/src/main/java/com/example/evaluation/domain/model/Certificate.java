package com.example.evaluation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {
	private String certificateId;
	private String degreeId;
	private String studentId;
}
