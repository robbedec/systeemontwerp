package com.example.evaluation.application;

public interface CertificateService {
	Response generateCertificate(String degreeId, String studentId);
	Response verifyCertificate(String certificateId);
}
