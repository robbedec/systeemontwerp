package be.ugent.timgeldof.learning_platform.application;


import java.time.LocalDateTime;
import java.util.ArrayList;

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
import be.ugent.timgeldof.learning_platform.domain.course_access.StudentNotFoundException;

@Transactional
@Service
public class LearningPlatformServiceImpl implements LearningPlatformService{

	private static final Logger log = LoggerFactory.getLogger(LearningPlatformServiceImpl.class);

	
	@Autowired
	CourseRepository courseRepo; 

	@Autowired
	CourseAccessRepository courseAccessRepo; 
	
	@Override
	public Response publishCourseMaterial(String courseId, byte[] file, String fileName) {
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
	public Response changeCourseMaterialVisibility(String courseId) {
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
	public Response addCourseAnnouncements(String courseId, String message) {
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
	public Response addCourse(String courseId, String courseName, Integer courseCredits, String teacherId) {
		try {
			Course c = new Course();
			c.setId(courseId);
			c.setCourseName(courseName);
			c.setCourseCredits(courseCredits);
			c.setTeacherId(teacherId);
			
			this.courseRepo.save(c);
			log.info(courseName + " was successfully added");
			return new Response(ResponseStatus.SUCCESS,"course added: " + c.getCourseName());
		}
		catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"The course could not be added");
		}
	}

	@Override
	public Response removeCourse(String courseId) {
		try {
			Course c = this.courseRepo.findOne(courseId);
			this.courseRepo.remove(c);
			this.courseAccessRepo.removeCourse(c);
			log.info(c.getCourseName() + " was successfully deleted");
			return new Response(ResponseStatus.SUCCESS,"course removed: " + c.getCourseName());
		}
		catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"The course could not be removed");
		}
	}

	@Override
	public Response registerInvoiceOverdue(String studentId) {
		try {
			CourseAccess ca = this.courseAccessRepo.findById(studentId);
			ca.setInvoiceOpen(true);
			this.courseAccessRepo.save(ca);
			
			return new Response(ResponseStatus.SUCCESS,"course access denied because invoice has been not paid by: " + ca.getStudentId());
		}
		catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"invoice payment overdue not registered");
		} catch (StudentNotFoundException e) {
			return new Response(ResponseStatus.FAIL,"student not found");
		}
	}

	@Override
	public Response registerPlagiarism(String studentId) {
		try {
			CourseAccess ca = this.courseAccessRepo.findById(studentId);
			// TODO : see if changetype is implemented
			ca.setUndergoingPlagiarismProcedure(true);
			this.courseAccessRepo.save(ca);
			return new Response(ResponseStatus.SUCCESS,"course access denied because of plagiarism for: " + ca.getStudentId());
		}
		catch (RuntimeException e) {
			return new Response(ResponseStatus.FAIL,"The plagiarism was not registered successfully");
		}catch (StudentNotFoundException e) {
			return new Response(ResponseStatus.FAIL,"student not found");
		}
	}

	@Override
	public Response changeCurriculum(String changeType, String studentId, String courseId) {

		try {
			CourseAccess ca = this.courseAccessRepo.findById(studentId);
			ca.setInvoiceOpen(false);
			ca.setUndergoingPlagiarismProcedure(false);
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
		} catch (StudentNotFoundException e) {
			// CourseAccess object does not exist yet
			if(changeType.equalsIgnoreCase("ADDED")) {
				CourseAccess ca = new CourseAccess();
				ca.setStudentId(studentId);
				ca.setInvoiceOpen(false);
				ca.setUndergoingPlagiarismProcedure(false);
				ca.setCourseIds(new ArrayList<String>());
				ca.addCourse(courseId);
				this.courseAccessRepo.save(ca);
				return new Response(ResponseStatus.SUCCESS,"Curriculum change successful for: " + ca.getStudentId());
			}
			else {
				return new Response(ResponseStatus.SUCCESS,"Curriculum course removal was not necessary "
						+ "since the student did not have access to the course anyway");
			}
		}
	}
}

