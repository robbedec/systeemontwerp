package be.ugent.systemdesign.university.curriculum.domain;

import java.util.List;

public interface CurriculumRepository {
	
	Curriculum findByCurriculumId(String curriculumId);
	Curriculum findByStudentId(String studentId);
	Curriculum findByStudentIdAndFacultyNameAndDegreeName(String studentId, String facultyName, String degreeName);
	
	void save(Curriculum c);
}
