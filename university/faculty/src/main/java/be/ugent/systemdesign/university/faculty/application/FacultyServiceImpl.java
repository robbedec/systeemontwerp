package be.ugent.systemdesign.university.faculty.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.university.faculty.domain.Course;
import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.domain.FacultyRepository;

@Transactional
@Service
public class FacultyServiceImpl implements FacultyService {
	
	@Autowired
	FacultyRepository facultyRepo;

	@Override
	public Response addCourseToFaculty(Long facultyId, String courseName, Integer courseCredits) {
		
		try {
			Faculty f = facultyRepo.findByFacultyId(facultyId);
			f.addCourse(new Course(courseName, courseCredits, f));
			facultyRepo.save(f);
			
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Operation failed");
		}
		
		return new Response(ResponseStatus.SUCCESS, "Course added");
	}

	@Override
	public Response removeCourseFromFaculty(Long facultyId, String courseName, Integer courseCredits) {
		try {
			Faculty f = facultyRepo.findByFacultyId(facultyId);
			f.removeCourse(new Course(courseName, courseCredits, f));
			facultyRepo.save(f);
			
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Operation failed");
		}
		
		return new Response(ResponseStatus.SUCCESS, "Course removed");
	}
	
	

}
