package be.ugent.timgeldof.learning_platform.application;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.ugent.timgeldof.learning_platform.domain.course.Course;
import be.ugent.timgeldof.learning_platform.domain.course.CourseAnnouncement;
import be.ugent.timgeldof.learning_platform.domain.course.CourseMaterial;
import be.ugent.timgeldof.learning_platform.domain.course.CourseRepository;

@Transactional
@Service
public class LearningPlatformServiceImpl implements LearningPlatformService{

	@Autowired
	CourseRepository courseRepo; 

	
	@Override
	public Response publishCourseMaterial(Integer courseId, byte[] file, String fileName) {
		CourseMaterial cm = new CourseMaterial();
		cm.setFile(file);
		cm.setName(fileName);
		// when new course material is added, it won't be visible before making it visible
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
}
