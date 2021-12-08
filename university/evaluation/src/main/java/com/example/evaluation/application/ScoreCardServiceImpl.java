package com.example.evaluation.application;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Transactional
@Service
public class ScoreCardServiceImpl implements ScoreCardService {

	@Override
	public Response generateScoreCards(String degreeId) {
		
		return new Response(ResponseStatus.SUCCESS, "Score cards generated");
	}
	
}
