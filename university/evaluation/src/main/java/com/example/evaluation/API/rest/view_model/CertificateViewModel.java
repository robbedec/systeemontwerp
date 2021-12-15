package com.example.evaluation.API.rest.view_model;

import com.example.evaluation.application.query.read_model.CertificateReadModel;

public class CertificateViewModel {

	public final String certificateId;
	public final String degreeId;

	public CertificateViewModel(CertificateReadModel certificateRM) {
		certificateId = certificateRM.certificateId;
		degreeId = certificateRM.degreeId;
	}

}
