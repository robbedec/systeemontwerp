package be.ugent.systemdesign.university.curriculum.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.university.curriculum.domain.Course;
import be.ugent.systemdesign.university.curriculum.domain.Faculty;
import be.ugent.systemdesign.university.curriculum.domain.FacultyCoursesRepository;

@Repository
public class FacultyCoursesRepositoryImpl implements FacultyCoursesRepository {

	@Autowired
	FacultyCoursesDataModelRepository facultyDMRepo;
	
	@Override
	public Faculty findByFacultyName(String facultyName) {
		FacultyDataModel dataModel = facultyDMRepo.findByFacultyName(facultyName).orElseThrow(FacultyNotFoundException::new);
		return mapToFaculty(dataModel);
	}

	@Override
	public void save(Faculty f) {
		FacultyDataModel dataModel = mapToFacultyDataModel(f);
		facultyDMRepo.save(dataModel);
		
	}
	
	private FacultyDataModel mapToFacultyDataModel(Faculty f) {
		return new FacultyDataModel(
				f.getFacultyId(),
				f.getFacultyName(),
				f.getAvailableCourses()
		);
	}
	
	private Faculty mapToFaculty(FacultyDataModel f) {
		return Faculty.builder()
				.facultyId(f.getFacultyId())
				.facultyName(f.getFacultyName())
				.availableCourses(f.getAvailable_courses()
					.stream()
					.map(t -> Course.builder()
							.name(t.getName())
							.credits(t.getCredits())
							.build())
					.collect(Collectors.toList()))
				.build();
	}

}
