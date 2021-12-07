package be.ugent.systemdesign.university.curriculum.infrastructure;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacultyCoursesDataModelRepository extends MongoRepository<FacultyDataModel, String> {
	
	Optional<FacultyDataModel> findByFacultyName(String facultyName);
}
