package be.ugent.timgeldof.learning_platform.API.rest;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.timgeldof.learning_platform.application.LearningPlatformService;
import be.ugent.timgeldof.learning_platform.application.Response;
import be.ugent.timgeldof.learning_platform.application.ResponseStatus;
import be.ugent.timgeldof.learning_platform.application.query.CourseQuery;
import be.ugent.timgeldof.learning_platform.application.query.CourseViewModel;
import be.ugent.timgeldof.learning_platform.application.query.CourseWithCourseAnnouncementsViewModel;
import be.ugent.timgeldof.learning_platform.application.query.CourseWithCourseMaterialViewModel;
import be.ugent.timgeldof.learning_platform.domain.course_access.CourseAccessDeniedException;
import be.ugent.timgeldof.learning_platform.infrastructure.course.CourseQueryImpl;


@RestController
@RequestMapping(path="api/learningplatform/")
@CrossOrigin(origins="*")
public class LearningPlatformController {

	@Autowired
	CourseQuery q;
	
	@Autowired
	LearningPlatformService s;
	
	// GET 
	@GetMapping("courses/")
	public String testRestController(){
		return "controller working";
	}
	
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
	
	// PUT
	
	@PutMapping("course/{courseId}/materials/add")
	public ResponseEntity<String> publishCourseMaterial(@RequestBody CourseMaterialViewModel courseMaterialViewModel, @PathVariable("courseId") String courseId) {
        byte[] file = Base64.getEncoder().encode(courseMaterialViewModel.getFileBase64().getBytes());	
		Response response = s.publishCourseMaterial(Integer.parseInt(courseId), file, courseMaterialViewModel.getFileName());
		
		return createResponseEntity(
				response.status, 
				"Course material " + courseMaterialViewModel.getFileName() + " added.", 
				HttpStatus.OK, 
				response.message, 
				HttpStatus.BAD_REQUEST
			);
	}
	
	@PutMapping("course/{courseId}/materials/makevisible")
	public ResponseEntity<String> makeCourseMaterialVisible(@PathVariable("courseId") String courseId) {
				
		Response response = s.changeCourseMaterialVisibility(Integer.parseInt(courseId));
		
		return createResponseEntity(
				response.status, 
				"Course material made visible.", 
				HttpStatus.OK, 
				response.message, 
				HttpStatus.BAD_REQUEST
			);
	}
	
	@PutMapping("course/{courseId}/announcements/add")
	public ResponseEntity<String> addCourseAnnouncement(@RequestBody CourseAnnouncementViewModel courseAnnouncementViewModel, @PathVariable("courseId") String courseId) {
				
		Response response = s.addCourseAnnouncements(Integer.parseInt(courseId), courseAnnouncementViewModel.getMessage());
		
		return createResponseEntity(
				response.status, 
				"Course material announcement added for course with ID " + courseId, 
				HttpStatus.OK, 
				response.message, 
				HttpStatus.BAD_REQUEST
			);
	}

	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
		if(status == ResponseStatus.FAIL) 
			return new ResponseEntity<>(sadMessage, sadStatus);		
		return new ResponseEntity<>(happyMessage,happyStatus);
	}
	
	  @ExceptionHandler(CourseAccessDeniedException.class)
	  public ResponseEntity<String> handleNoSuchElementFoundException(CourseAccessDeniedException exception) {
	    return ResponseEntity
	        .status(HttpStatus.UNAUTHORIZED)
	        .body(exception.getMessage());
	  }
	
}
