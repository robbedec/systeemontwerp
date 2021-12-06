package com.example.evaluation;

import org.slf4j.LoggerFactory;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.evaluation.infrastructure.CertificateDataModel;
import com.example.evaluation.infrastructure.CertificateDataModelRepository;
import com.example.evaluation.infrastructure.TaskDataModel;
import com.example.evaluation.infrastructure.TaskDataModelRepository;
import com.example.evaluation.infrastructure.TaskSubmissionDataModel;
import com.example.evaluation.infrastructure.TaskSubmissionDataModelRepository;

@SpringBootApplication
public class EvaluationApplication {
	
	private static final Logger log = LoggerFactory.getLogger(EvaluationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EvaluationApplication.class, args);
	}
	
	@Bean
	CommandLineRunner testCertificateDataModelRespository(CertificateDataModelRepository cdmr) {
		return (args) -> {
			for(CertificateDataModel c : cdmr.findByStudentId("1")) {
				log.info("{} {} {}\n", c.getCertificateId(), c.getStudentId(), c.getCourseId());
			}
		};
	}
	
	@Bean
	CommandLineRunner testTaskDataModelRepository(TaskDataModelRepository tdmr) {
		return (args) -> {
			Optional<TaskDataModel> t = tdmr.findById("1");
			if(t.isPresent()) {
				log.info("{} {} {} {}", t.get().getTaskId(), t.get().getCourseId(), t.get().getDescription(), t.get().getDueDate());
			}
		};
	}
	
	@Bean
	CommandLineRunner testTaskSubmissionDataModelRepository(TaskSubmissionDataModelRepository tsdmr) {
		return (args) -> {
			Optional<TaskSubmissionDataModel> ts = tsdmr.findById("1");
			if(ts.isPresent()) {
				log.info("{} {} {} {} {} {}", ts.get().getSubmissionId(), ts.get().getTaskId(), ts.get().getStudentId(), ts.get().getFile(), ts.get().getDateSubmited(), ts.get().getScore());
			}
		};
	}

}
