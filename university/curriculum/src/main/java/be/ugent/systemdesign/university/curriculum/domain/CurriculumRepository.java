package be.ugent.systemdesign.university.curriculum.domain;

import java.util.List;

public interface CurriculumRepository {
	
	List<Curriculum> findByCurriculumId(String curriculumId);
	String save(Curriculum c);
}
