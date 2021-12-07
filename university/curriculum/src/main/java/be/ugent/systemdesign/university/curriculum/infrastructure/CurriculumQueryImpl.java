package be.ugent.systemdesign.university.curriculum.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.university.curriculum.application.query.CourseReadModel;
import be.ugent.systemdesign.university.curriculum.application.query.CurriculumQuery;
import be.ugent.systemdesign.university.curriculum.application.query.CurriculumReadModel;

@Component
public class CurriculumQueryImpl implements CurriculumQuery {

	@Autowired
	CurriculumDataModelRepository curriculumDMRepo;

	@Override
	public CurriculumReadModel getCurriculum(String curriculumId) {
		return mapToReadModel(curriculumDMRepo.findById(curriculumId).orElseThrow(CurriculumNotFoundException::new));
	}
	
	@Override
	public List<CurriculumReadModel> findAll() {
		return curriculumDMRepo.findAll().stream().map(cdm -> mapToReadModel(cdm)).collect(Collectors.toList());
	}
	
	private CurriculumReadModel mapToReadModel(CurriculumDataModel _c) {
		CurriculumReadModel crm = new CurriculumReadModel(
			_c.getCurriculumId(),
			_c.getCurriculumStatus(), 
			_c.getAcademicYear(), 
			_c.getCourses().stream()
				.map(c -> new CourseReadModel(
						c.getName(), 
						c.getCredits()
				)).collect(Collectors.toList()),
			_c.getFacultyName()
		);
		
		return crm;
	}
}
