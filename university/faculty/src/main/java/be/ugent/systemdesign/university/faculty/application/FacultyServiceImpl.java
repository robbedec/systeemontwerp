package be.ugent.systemdesign.university.faculty.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.systemdesign.university.faculty.FacultyApplication;
import be.ugent.systemdesign.university.faculty.domain.Course;
import be.ugent.systemdesign.university.faculty.domain.DegreeProgramme;
import be.ugent.systemdesign.university.faculty.domain.Faculty;
import be.ugent.systemdesign.university.faculty.domain.FacultyRepository;

@Transactional
@Service
public class FacultyServiceImpl implements FacultyService {
	
	@Autowired
	FacultyRepository facultyRepo;
	
	Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

	@Override
	public Response addCourseToFaculty(String facultyName, String degreeName, String courseName, Integer courseCredits, Integer teacherId) {
		
		try {
			Faculty f = facultyRepo.findByFacultyName(facultyName);
			DegreeProgramme d = f.degrees.stream().filter(x -> x.getDegreeName().equals(degreeName)).findAny().orElse(null);
			
			d.addCourse(new Course(courseName, courseCredits, d, teacherId));
			facultyRepo.save(f);
			
		} catch (Exception e) {
			logger.info("hier {}", e);
			return new Response(ResponseStatus.FAIL, "Operation failed");
		}
		
		return new Response(ResponseStatus.SUCCESS, "Course added");
	}

	@Override
	public Response removeCourseFromFaculty(Long facultyId, Long degreeId, String courseName, Integer courseCredits) {
		try {
			Faculty f = facultyRepo.findByFacultyId(facultyId);
			DegreeProgramme d = f.degrees.stream().filter(x -> x.getDegreeId() == degreeId).findAny().orElse(null);
			
			d.removeCourse(new Course(courseName, courseCredits, d, 0));
			facultyRepo.save(f);
			
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Operation failed");
		}
		
		return new Response(ResponseStatus.SUCCESS, "Course removed");
	}
}
