package be.ugent.timgeldof.learning_platform.domain.course_access;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ugent.timgeldof.learning_platform.domain.course.Course;
import be.ugent.timgeldof.learning_platform.domain.course.CourseAnnouncement;
import be.ugent.timgeldof.learning_platform.domain.course.CourseMaterial;
import be.ugent.timgeldof.learning_platform.domain.course.CourseRepository;

@Component
public class CourseAccessDomainService{
	
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	CourseAccessRepository courseAccessRepo;	
	
	public List<Course> getAccessibleCourses(String studentId){
		CourseAccess ca = courseAccessRepo.findById(studentId);
		checkAccess(ca);
		List<Course> courses = new ArrayList<Course>();
		ca.getCourseIds().forEach(c_id -> {
			courses.add(courseRepo.findOne(c_id));
		});
		return courses;
	}
	
	public List<CourseAnnouncement> getAccessibleCourseAnnouncements(String studentId, Integer courseId){
		CourseAccess ca = courseAccessRepo.findById(studentId);
		checkAccess(ca);
		Course c = courseRepo.findOne(courseId);
		c.getCourseAnnouncements().size();
		List<CourseAnnouncement> courseAnnouncements = c.getCourseAnnouncements();
		return courseAnnouncements;
	}
	
	public List<CourseMaterial> getAccessibleCourseMaterials(String studentId, Integer courseId){
		CourseAccess ca = courseAccessRepo.findById(studentId);
		checkAccess(ca);
		return courseRepo.findOne(courseId).getVisibleCourseMaterials();
	}

	private void checkAccess(CourseAccess ca) {
		if(!ca.isStudentAllowedCourseAccess()) {
			throw new CourseAccessDeniedException();
		}
	}
}
