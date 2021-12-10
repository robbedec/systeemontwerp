package com.example.account.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.account.data.AccountRepository;

@RestController
@RequestMapping(path="api/account/")
@CrossOrigin(origins="*")
public class Controller {
	@Autowired
	AccountRepository accountRepo;
	
	@DeleteMapping(path="{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable String accountId) {
		accountRepo.deleteById(accountId);
		return new ResponseEntity<>("Account deleted", HttpStatus.OK);
	}
	
	@GetMapping(path="{id}/plagiarism")
	public List<String> getPlagiarismViolations(@PathVariable String accountId) {
		List<String> taskIds = new ArrayList<>();
		for(String comment : accountRepo.getById(accountId).getComments()) {
			String[] split = comment.split(" ");
			if(split[0].equals("Plagiarized"))
				taskIds.add(split[1]);
		}
		return taskIds;
	}
}
