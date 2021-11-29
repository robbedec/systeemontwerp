package be.ugent.systemdesign.university.curriculum.application.query;

import java.util.List;

public interface CurriculumQuery {

	public CurriculumReadModel getCurriculum(String curriculumId);
	public List<CurriculumReadModel> findAll();
}
