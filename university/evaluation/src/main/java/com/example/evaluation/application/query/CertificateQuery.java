package com.example.evaluation.application.query;

import java.util.List;

import com.example.evaluation.application.query.read_model.CertificateReadModel;
import com.example.evaluation.application.query.read_model.CertificateVerificationReadModel;

public interface CertificateQuery {

	List<CertificateReadModel> getCertificates(String studentId);

	CertificateVerificationReadModel verifyCertificate(String certificateId);

}
