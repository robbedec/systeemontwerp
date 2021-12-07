package com.example.evaluation.API.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaluation.API.rest.view_model.CertificateVerificationViewModel;
import com.example.evaluation.API.rest.view_model.CertificateViewModel;
import com.example.evaluation.application.CertificateService;
import com.example.evaluation.application.Response;
import com.example.evaluation.application.ResponseStatus;
import com.example.evaluation.application.query.CertificateQuery;

@RestController
@RequestMapping(path="api/evaluation/certificates/")
@CrossOrigin(origins="*")
public class CertificateController {
	
	@Autowired
	private CertificateQuery certificateQuery;
	
	@Autowired
	private CertificateService certificateService;
	
	@GetMapping("{id}/verify")
	public CertificateVerificationViewModel verifyCertificate(@PathVariable("id") String certificateId) {
		return new CertificateVerificationViewModel(certificateQuery.verifyCertificate(certificateId));
	}
	
	@PostMapping
	public ResponseEntity<String> generateCertificate(@RequestBody CertificateViewModel certificate) {
		Response response = certificateService.generateCertificate(certificate.studentId, certificate.degreeId);
		return createResponseEntity(response.status, "Certificate generated", HttpStatus.CREATED, response.message, HttpStatus.CONFLICT);
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String successMsg, HttpStatus successStatus, String failMsg, HttpStatus failStatus) {
		if(status == ResponseStatus.SUCCESS)
			return new ResponseEntity<>(successMsg, successStatus);
		return new ResponseEntity<>(failMsg, failStatus);
	}
}
