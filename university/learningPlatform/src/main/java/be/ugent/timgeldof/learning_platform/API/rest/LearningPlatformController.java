package be.ugent.timgeldof.learning_platform.API.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.timgeldof.learning_platform.application.query.CourseQuery;
import be.ugent.timgeldof.learning_platform.application.query.CourseViewModel;
import be.ugent.timgeldof.learning_platform.application.query.CourseWithCourseAnnouncementsViewModel;
import be.ugent.timgeldof.learning_platform.application.query.CourseWithCourseMaterialViewModel;
import be.ugent.timgeldof.learning_platform.infrastructure.course.CourseQueryImpl;


@RestController
@RequestMapping(path="api/learning_platform/")
@CrossOrigin(origins="*")
public class LearningPlatformController {

	@Autowired
	CourseQuery q;
	
	@GetMapping("courses/{studentId}")
	public List<CourseViewModel> getAvailableCourses(@PathVariable("studentId") String studentId){
		return q.getAvailableCourses(studentId);
	}
	
	@GetMapping("course/{courseId}/materials/{studentId}")
	public CourseWithCourseMaterialViewModel getCourseMaterial(@PathVariable("studentId") String studentId, @PathVariable("courseId") int courseId){
		return q.getCourseMaterial(studentId, courseId);
	}
	
	@GetMapping("course/{courseId}/announcements/{studentId}")
	public CourseWithCourseAnnouncementsViewModel getCourseAnnouncements(@PathVariable("studentId") String studentId, @PathVariable("courseId") int courseId){
		return q.getCourseAnnouncements(studentId, courseId);
	}
	
}
