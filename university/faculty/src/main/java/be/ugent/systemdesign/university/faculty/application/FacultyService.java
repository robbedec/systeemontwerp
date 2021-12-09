package be.ugent.systemdesign.university.faculty.application;

public interface FacultyService {
	
	Response addCourseToFaculty(String facultyName, String degreeName, String courseName, Integer courseCredits);
	Response removeCourseFromFaculty(Long facultyId, Long degreeId, String courseName, Integer courseCredits);
}
