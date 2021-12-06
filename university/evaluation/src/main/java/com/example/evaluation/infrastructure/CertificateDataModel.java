package com.example.evaluation.infrastructure;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDataModel {
	@Id
	private String certificateId;
	private String studentId;
	private String courseId;
}
