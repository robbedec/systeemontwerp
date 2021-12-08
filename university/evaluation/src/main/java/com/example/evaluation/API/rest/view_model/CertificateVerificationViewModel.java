package com.example.evaluation.API.rest.view_model;

import com.example.evaluation.application.query.CertificateVerificationReadModel;

public class CertificateVerificationViewModel {
	public final String valid;
	
	public CertificateVerificationViewModel(CertificateVerificationReadModel certificateRM) {
		valid = certificateRM.valid ? "Yes" : "No";
	}
}
