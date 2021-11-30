package be.ugent.systemdesign.university.curriculum.API.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.university.curriculum.application.CurriculumService;
import be.ugent.systemdesign.university.curriculum.application.Response;
import be.ugent.systemdesign.university.curriculum.application.ResponseStatus;
import be.ugent.systemdesign.university.curriculum.application.query.CurriculumQuery;

@RestController
@RequestMapping(path="api/curriculum/")
@CrossOrigin(origins="*")
public class CurriculumController {

	@Autowired
	CurriculumService curriculumService;
	
	@Autowired
	CurriculumQuery curriculumQuery;
	
	@GetMapping
	public List<CurriculumViewModel> findAll() {
		return curriculumQuery.findAll().stream().map(c -> new CurriculumViewModel(c)).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public CurriculumViewModel findCurriculumWithId(@PathVariable("id") String curriculumId) {
		return new CurriculumViewModel(curriculumQuery.getCurriculum(curriculumId));
	}
	
	@PutMapping("{id}/markproposed")
	public ResponseEntity<String> markCurriculumAsProposed(@PathVariable("id") String curriculumId) {
		Response response = curriculumService.markCurriculumAsProposed(curriculumId);
		
		return createResponseEntity(
			response.status, 
			"Marked as proposed",
			HttpStatus.OK, 
			response.message,
			HttpStatus.CONFLICT 
		);
	}
	
	@GetMapping("{id}/change")
	public void changeCurriculumWithId(@PathVariable("id") String curriculumId, @RequestParam("accountId") String accountId) {
		Response response = curriculumService.changeCurriculum(curriculumId, accountId);
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}
}
