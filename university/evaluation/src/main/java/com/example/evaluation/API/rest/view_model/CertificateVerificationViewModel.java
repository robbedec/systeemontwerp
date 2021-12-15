package com.example.evaluation.API.rest.view_model;

import com.example.evaluation.application.query.read_model.CertificateVerificationReadModel;

public class CertificateVerificationViewModel {

	public final String valid;

	public CertificateVerificationViewModel(CertificateVerificationReadModel certificateRM) {
		valid = certificateRM.valid ? "Yes" : "No";
	}

}
