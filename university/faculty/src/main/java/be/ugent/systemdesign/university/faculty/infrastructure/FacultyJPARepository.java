package be.ugent.systemdesign.university.faculty.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ugent.systemdesign.university.faculty.domain.Faculty;

public interface FacultyJPARepository extends JpaRepository<Faculty, Integer> {
	List<Faculty> findAll();
	Faculty findByFacultyName(String facultyName);
	

}
