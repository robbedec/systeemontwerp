package be.ugent.systemdesign.university.faculty.application;

public interface FacultyService {
	
	Response addCourseToFaculty(Long facultyId, String courseName, Integer courseCredits);
	Response removeCourseFromFaculty(Long facultyId, String courseName, Integer courseCredits);
}
