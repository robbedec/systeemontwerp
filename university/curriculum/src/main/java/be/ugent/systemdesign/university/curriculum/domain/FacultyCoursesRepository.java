package be.ugent.systemdesign.university.curriculum.domain;

import java.util.List;

public interface FacultyCoursesRepository {
	
	Faculty findByFacultyName(String facultyName);
	void save(Faculty f);
}
