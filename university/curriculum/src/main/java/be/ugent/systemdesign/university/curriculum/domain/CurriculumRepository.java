package be.ugent.systemdesign.university.curriculum.domain;

import java.util.List;

public interface CurriculumRepository {
	
	Curriculum findByCurriculumId(String curriculumId);
	Curriculum findByStudentId(String studentId);
	
	void save(Curriculum c);
}
