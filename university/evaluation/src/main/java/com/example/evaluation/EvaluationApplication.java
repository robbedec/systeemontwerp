package com.example.evaluation;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.example.evaluation.API.messaging.Channels;

@SuppressWarnings("deprecation")
@SpringBootApplication
//@EnableBinding(Channels.class)
public class EvaluationApplication {

	private static final Logger log = LoggerFactory.getLogger(EvaluationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EvaluationApplication.class, args);
	}

}
