package com.example.evaluation.API.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaluation.API.rest.view_model.ScoreCardViewModel;
import com.example.evaluation.application.Response;
import com.example.evaluation.application.ResponseStatus;
import com.example.evaluation.application.ScoreCardService;
import com.example.evaluation.application.query.ScoreCardQuery;

@RestController
@RequestMapping(path="api/evaluation/scorecards/")
@CrossOrigin(origins="*")
public class ScoreCardController {
	@Autowired
	ScoreCardQuery scoreCardQuery;
	
	@Autowired
	ScoreCardService scoreCardService;
	
	@GetMapping("{id}")
	public ScoreCardViewModel getScoreCard(@PathVariable String degreeId, String studentId) {
		return new ScoreCardViewModel(scoreCardQuery.getScoreCard(studentId, degreeId));
	}
	
	@PostMapping("{id}/generate")
	public ResponseEntity<String> generateScoreCards(String degreeId) {
		Response response = scoreCardService.generateScoreCards(degreeId);
		return createResponseEntity(response.status, "Generated score cards", HttpStatus.OK, response.message, HttpStatus.CONFLICT);
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String successMsg, HttpStatus successStatus, String failMsg, HttpStatus failStatus) {
		if(status == ResponseStatus.SUCCESS)
			return new ResponseEntity<>(successMsg, successStatus);
		return new ResponseEntity<>(failMsg, failStatus);
	}
}
