package be.ugent.systemdesign.university.faculty.domain;

public interface FacultyRepository {
	
	public Faculty findByFacultyName(String _name);
	public void save(Faculty _f);
	public Faculty findByFacultyId(Long _id);
}
