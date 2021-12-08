package be.ugent.timgeldof.learning_platform.application;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.timgeldof.learning_platform.application.event.EventHandler;
import be.ugent.timgeldof.learning_platform.domain.course.Course;
import be.ugent.timgeldof.learning_platform.domain.course.CourseAnnouncement;
import be.ugent.timgeldof.learning_platform.domain.course.CourseMaterial;
import be.ugent.timgeldof.learning_platform.domain.course.CourseRepository;
import be.ugent.timgeldof.learning_platform.domain.course_access.CourseAccess;
import be.ugent.timgeldof.learning_platform.domain.course_access.CourseAccessRepository;

@Transactional
@Service
public class LearningPlatformServiceImpl implements LearningPlatformService{

	private static final Logger log = LoggerFactory.getLogger(LearningPlatformServiceImpl.class);

	
	@Autowired
	CourseRepository courseRepo; 

	@Autowired
	CourseAccessRepository courseAccessRepo; 
	
	@Override
	public Response publishCourseMaterial(Integer courseId, byte[] file, String fileName) {
		CourseMaterial cm = new CourseMaterial();
		cm.setFile(file);
		cm.setName(fileName);
		// when new course material is added, it won't be visible before actually making it visible
		cm.setVisible(false);
		cm.setTimestamp(LocalDateTime.now());
		try {
			Course c = courseRepo.findOne(courseId);
			c.addCourseMaterial(cm);
			courseRepo.save(c);
			return new Response(ResponseStatus.SUCCESS,"course id: " + c.getId());
		} catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"The course material could not be added");
		}
	}

	@Override
	public Response changeCourseMaterialVisibility(Integer courseId) {
		try {
			Course c = courseRepo.findOne(courseId);
			c.changeCourseMaterialVisibility(true);
			courseRepo.save(c);
			return new Response(ResponseStatus.SUCCESS,"course id: " + c.getId());
		} catch(RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"The course material could not be made visible");
		}
	}

	@Override
	public Response addCourseAnnouncements(int courseId, String message) {
		CourseAnnouncement ca = new CourseAnnouncement();
		ca.setMessage(message);
		ca.setTimeStamp(LocalDateTime.now());
		try {
			Course c = courseRepo.findOne(courseId);
			c.addCourseAnnouncement(ca);
			courseRepo.save(c);
			return new Response(ResponseStatus.SUCCESS,"course id: " + c.getId());
		} catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"The course material could not be added");
		}
	}

	@Override
	public Response addCourse(String courseName, Integer courseCredits) {
		try {
			Course c = new Course();
			c.setCourseName(courseName);
			c.setCourseCredits(courseCredits);
			
			this.courseRepo.save(c);
			log.info(courseName + " was successfully added");
			return new Response(ResponseStatus.SUCCESS,"course added: " + c.getCourseName());
		}
		catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"The course could not be added");
		}
	}

	@Override
	public Response removeCourse(String courseName, Integer courseCredits) {
		try {
			Course c = new Course();
			c.setCourseName(courseName);
			c.setCourseCredits(courseCredits);
			this.courseRepo.remove(c);
			this.courseAccessRepo.removeCourse(c);
			log.info(courseName + " was successfully deleted");
			return new Response(ResponseStatus.SUCCESS,"course removed: " + c.getCourseName());
		}
		catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"The course could not be removed");
		}
	}

	@Override
	public Response registerInvoicePaid(String studentId) {
		try {
			CourseAccess ca = this.courseAccessRepo.findById(studentId);
			ca.setInvoiceOpen(false);
			this.courseAccessRepo.save(ca);
			return new Response(ResponseStatus.SUCCESS,"course access granted because invoice has been paid by: " + ca.getStudentId());
		}
		catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"invoice payment not registered");
		}
	}

	@Override
	public Response registerPlagiarism(String studentId, String changeType) {
		try {
			CourseAccess ca = this.courseAccessRepo.findById(studentId);
			// TODO : see if changetype is implemented
			ca.setUndergoingPlagiarismProcedure(true);
			this.courseAccessRepo.save(ca);
			return new Response(ResponseStatus.SUCCESS,"course access denied because of plagiarism for: " + ca.getStudentId());
		}
		catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"The plagiarism was not registered successfully");
		}
	}

	@Override
	public Response changeCurriculum(String changeType, String studentId, String courseCredits, String courseName) {

		try {
			CourseAccess ca = this.courseAccessRepo.findById(studentId);
			Integer courseId = this.courseRepo.findByCourseNameAndCourseCredits(courseName, courseCredits).getId();
			if(changeType.equalsIgnoreCase("ADDED")) {
				ca.addCourse(courseId);
			} else {
				ca.removeCourse(courseId);
			}
			this.courseAccessRepo.save(ca);
			return new Response(ResponseStatus.SUCCESS,"Curriculum change successful for: " + ca.getStudentId());
		}
		catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"The curriculum change was not registered successfully");
		}
	}
}

