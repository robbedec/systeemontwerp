package be.ugent.systemdesign.university.curriculum.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurriculumDataModelRepository extends MongoRepository<CurriculumDataModel, String> {

	CurriculumDataModel findByCurriculumId(String curriculumId);
	Optional<CurriculumDataModel> findByStudentId(String studenId);
	List<CurriculumDataModel> findByStudentIdAndFacultyNameAndDegreeName(String studentId, String facultyName, String degreeName);
}
