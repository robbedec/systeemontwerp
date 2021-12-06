package com.example.evaluation.domain;

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
	private String studentId;
	private String courseId;
}
