package be.ugent.systemdesign.university.curriculum.infrastructure;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurriculumDataModelRepository extends MongoRepository<CurriculumDataModel, String> {

	List<CurriculumDataModel> findByCurriculumId(String curriculumId);
}
