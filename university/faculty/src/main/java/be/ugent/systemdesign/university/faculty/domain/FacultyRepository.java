package be.ugent.systemdesign.university.faculty.domain;

import java.util.List;

public interface FacultyRepository {
	public List<Faculty> findAll();
	public Faculty findByFacultyName(String _name);
	public void save(Faculty _f);
	public Faculty findByFacultyId(Long _id);
}
