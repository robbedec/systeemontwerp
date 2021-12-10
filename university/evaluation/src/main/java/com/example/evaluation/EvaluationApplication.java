package com.example.evaluation;

import org.slf4j.LoggerFactory;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import com.example.evaluation.API.messaging.Channels;
import com.example.evaluation.application.CertificateService;
import com.example.evaluation.application.Response;
import com.example.evaluation.application.TaskService;
import com.example.evaluation.application.TaskServiceImpl;
import com.example.evaluation.infrastructure.data_model.CertificateDataModel;
import com.example.evaluation.infrastructure.data_model.TaskDataModel;
import com.example.evaluation.infrastructure.data_model.TaskSubmissionDataModel;
import com.example.evaluation.infrastructure.repository.CertificateDataModelRepository;
import com.example.evaluation.infrastructure.repository.TaskDataModelRepository;
import com.example.evaluation.infrastructure.repository.TaskSubmissionDataModelRepository;

@SpringBootApplication
@EnableBinding(Channels.class)
public class EvaluationApplication {
	
	private static final Logger log = LoggerFactory.getLogger(EvaluationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EvaluationApplication.class, args);
	}

}
