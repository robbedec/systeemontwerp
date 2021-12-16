package com.example.evaluation.API.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaluation.API.rest.view_model.ScoreCardViewModel;
import com.example.evaluation.application.service.Response;
import com.example.evaluation.application.service.ResponseStatus;
import com.example.evaluation.application.service.ScoreCardService;
import com.example.evaluation.application.query.ScoreCardQuery;

import org.slf4j.Logger;

@RestController
@RequestMapping(path = "api/evaluation/scorecards")
@CrossOrigin(origins = "*")
public class ScoreCardController {
	
	private static final Logger log = LoggerFactory.getLogger(ScoreCardController.class);

	@Autowired
	ScoreCardQuery scoreCardQuery;

	@Autowired
	ScoreCardService scoreCardService;

	@GetMapping
	public List<ScoreCardViewModel> getScoreCard(String studentId) {
		log.info("getScoreCard");
		return scoreCardQuery.getScoreCards(studentId).stream().map(scoreCardRM -> new ScoreCardViewModel(scoreCardRM))
				.collect(Collectors.toList());
	}

	@PostMapping("generate")
	public ResponseEntity<String> generateScoreCards() {
		log.info("generateScoreCards");
		Response response = scoreCardService.generateScoreCards();
		return createResponseEntity(response.status, "Generated score cards", HttpStatus.OK, response.message,
				HttpStatus.CONFLICT);
	}

	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String successMsg,
			HttpStatus successStatus, String failMsg, HttpStatus failStatus) {
		if (status == ResponseStatus.SUCCESS)
			return new ResponseEntity<>(successMsg, successStatus);
		log.info("FAILED {}", failMsg);
		return new ResponseEntity<>(failMsg, failStatus);
	}

}
