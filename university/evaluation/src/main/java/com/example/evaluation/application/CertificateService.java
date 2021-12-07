package com.example.evaluation.application;

public interface CertificateService {
	Response createCertificate(String degreeId, String studentId);
	Response verifyCertificate(String certificateId);
}
