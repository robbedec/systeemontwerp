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
import be.ugent.timgeldof.learning_platform.domain.course_access.StudentNotFoundException;
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
	public  List<CourseViewModel> testRestController(){
		return q.getAllCourses();
	}
	
	@GetMapping("students/{studentId}/courses/")
	public List<CourseViewModel> getAvailableCourses(@PathVariable("studentId") Integer studentId) throws StudentNotFoundException{
		return q.getAvailableCourses(studentId);
	}
	
	@GetMapping("students/{studentId}/courses/{courseId}/materials/")
	public CourseWithCourseMaterialViewModel getCourseMaterial(@PathVariable("studentId") Integer studentId, @PathVariable("courseId") Integer courseId) throws StudentNotFoundException{
		return q.getCourseMaterial(studentId, courseId);
	}
	
	@GetMapping("students/{studentId}/courses/{courseId}/announcements")
	public CourseWithCourseAnnouncementsViewModel getCourseAnnouncements(@PathVariable("studentId") Integer studentId, @PathVariable("courseId") Integer courseId) throws StudentNotFoundException{
		return q.getCourseAnnouncements(studentId, courseId);
	}
	
	// PUT
	
	@PutMapping("teachers/{teacherId}/courses/{courseId}/materials/add")
	public ResponseEntity<String> publishCourseMaterial(@RequestBody CourseMaterialViewModel courseMaterialViewModel, @PathVariable("courseId") Integer courseId) {
        byte[] file = Base64.getEncoder().encode(courseMaterialViewModel.getFileBase64().getBytes());	
		Response response = s.publishCourseMaterial(courseId, file, courseMaterialViewModel.getFileName());
		
		return createResponseEntity(
				response.status,
				"Course material " + courseMaterialViewModel.getFileName() + " added.", 
				HttpStatus.OK, 
				response.message, 
				HttpStatus.BAD_REQUEST
			);
	}
	
	@PutMapping("teachers/{teacherId}/courses/{courseId}/materials/makevisible")
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
	
	@PutMapping("teachers/{teacherId}/courses/{courseId}/announcements/add")
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
	  public ResponseEntity<String> handleCourseAccessDeniedException(CourseAccessDeniedException exception) {
	    return ResponseEntity
	        .status(HttpStatus.UNAUTHORIZED)
	        .body("COURSE_ACCESS_DENIED");
	  }
	  
	  @ExceptionHandler(StudentNotFoundException.class)
	  public ResponseEntity<String> handleNoSuchElementFoundException(StudentNotFoundException exception) {
	    return ResponseEntity
	        .status(HttpStatus.UNAUTHORIZED)
	        .body("STUDENT_NOT_FOUND");
	  }
	
}
