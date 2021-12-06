package com.example.evalutation;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.evalutation.infrastructure.CertificateDataModel;
import com.example.evalutation.infrastructure.CertificateDataModelRepository;
import com.example.evalutation.infrastructure.TaskDataModel;
import com.example.evalutation.infrastructure.TaskDataModelRepository;
import com.example.evalutation.infrastructure.TaskSubmissionDataModel;

@SpringBootApplication
public class EvaluationApplication {
	
	private static final Logger log = LoggerFactory.getLogger(EvaluationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EvaluationApplication.class, args);
	}
	
	@Bean
	CommandLineRunner testCertificateDataModelRespository(CertificateDataModelRepository cdmr) {
		return (args) -> {
			for(CertificateDataModel c : cdmr.findByStudentId(1)) {
				log.info("{} {} {}\n", c.getCertificateId(), c.getStudentId(), c.getCourseId());
			}
		};
	}
	
	@Bean
	CommandLineRunner testTaskDataModelRepository(TaskDataModelRepository tdmr) {
		return (args) -> {
			Optional<TaskDataModel> t = tdmr.findById(1);
			if(t.isPresent()) {
				log.info("{} {} {} {}", t.get().getTaskId(), t.get().getCourseId(), t.get().getDescription(), t.get().getDueDate());
				List<TaskSubmissionDataModel> ts = t.get().getSubmissions();
				for(TaskSubmissionDataModel s : ts) {
					log.info("\t{} {} {} {}/20", s.getStudentId(), s.getZipFile(), s.getDateSubmited(), s.getScore());
				}
			}
		};
	}

}
