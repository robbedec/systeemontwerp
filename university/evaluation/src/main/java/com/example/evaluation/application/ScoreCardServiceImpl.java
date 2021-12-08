package com.example.evaluation.application;

public class ScoreCardServiceImpl implements ScoreCardService {

	@Override
	public Response generateScoreCards(String degreeId) {
		
		return new Response(ResponseStatus.SUCCESS, "Score cards generated");
	}
	
}
