package com.example.evaluation.API.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaluation.API.rest.view_model.CertificateVerificationViewModel;
import com.example.evaluation.API.rest.view_model.CertificateViewModel;
import com.example.evaluation.application.query.CertificateQuery;

@RestController
@RequestMapping(path = "api/evaluation/certificates")
@CrossOrigin(origins = "*")
public class CertificateController {
	Logger log = LoggerFactory.getLogger(CertificateController.class);
	
	@Autowired
	private CertificateQuery certificateQuery;

	@GetMapping
	public List<CertificateViewModel> getCertificates(String studentId) {
		log.info("cert {}", studentId);
		return certificateQuery.getCertificates(studentId).stream()
				.map(certificateRM -> new CertificateViewModel(certificateRM)).collect(Collectors.toList());
	}

	@GetMapping("{id}/verify")
	public CertificateVerificationViewModel verifyCertificate(@PathVariable("id") String certificateId) {
		return new CertificateVerificationViewModel(certificateQuery.verifyCertificate(certificateId));
	}

}
