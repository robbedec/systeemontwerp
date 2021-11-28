package be.ugent.systemdesign.university.curriculum.API.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.systemdesign.university.curriculum.application.CurriculumService;
import be.ugent.systemdesign.university.curriculum.application.query.CurriculumQuery;

@RestController
@RequestMapping(path="/api/curriculum/")
@CrossOrigin(origins="*")
public class CurriculumController {

	@Autowired
	CurriculumService curriculumService;
	
	@Autowired
	CurriculumQuery curriculumQuery;
	
	@GetMapping
	public CurriculumViewModel findCurriculumWithId(@RequestParam("curriculumId") String curriculumId) {
		return new CurriculumViewModel(curriculumQuery.getCurriculum(curriculumId));
	}
}
