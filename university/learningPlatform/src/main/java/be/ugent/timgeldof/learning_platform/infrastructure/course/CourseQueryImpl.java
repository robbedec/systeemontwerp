package be.ugent.timgeldof.learning_platform.infrastructure.course;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ugent.timgeldof.learning_platform.API.messaging.MessageInputGateway;
import be.ugent.timgeldof.learning_platform.application.query.CourseAnnouncementViewModel;
import be.ugent.timgeldof.learning_platform.application.query.CourseMaterialViewModel;
import be.ugent.timgeldof.learning_platform.application.query.CourseQuery;
import be.ugent.timgeldof.learning_platform.application.query.CourseViewModel;
import be.ugent.timgeldof.learning_platform.application.query.CourseWithCourseAnnouncementsViewModel;
import be.ugent.timgeldof.learning_platform.application.query.CourseWithCourseMaterialViewModel;
import be.ugent.timgeldof.learning_platform.domain.course.CourseAnnouncement;
import be.ugent.timgeldof.learning_platform.domain.course.CourseMaterial;
import be.ugent.timgeldof.learning_platform.domain.course.CourseRepository;
import be.ugent.timgeldof.learning_platform.domain.course_access.CourseAccess;
import be.ugent.timgeldof.learning_platform.domain.course_access.CourseAccessDomainService;
import be.ugent.timgeldof.learning_platform.domain.course_access.CourseAccessRepository;
import be.ugent.timgeldof.learning_platform.domain.course_access.StudentNotFoundException;

@Component
public class CourseQueryImpl implements CourseQuery{

	private static final Logger log = LoggerFactory.getLogger(CourseQueryImpl.class);

	
	@Autowired
	private CourseAccessDomainService courseAccessDomainService;
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public List<CourseViewModel> getAvailableCourses(String studentId) throws StudentNotFoundException {
		return courseAccessDomainService.getAccessibleCourses(studentId)
				.stream()
				.map(c ->{
					log.info("returning course " + c.getCourseName() + "with ID: " + c.getId());
					return new CourseViewModel(c.getCourseName(), c.getId(), c.getTeacherId());
				 })
				.collect(Collectors.toList());
	}

	@Override
	public CourseWithCourseAnnouncementsViewModel getCourseAnnouncements(String studentId, String courseId) throws StudentNotFoundException {
		String courseName = courseRepo.findOne(courseId).getCourseName();
		List<CourseAnnouncement> courseAnnouncements = courseAccessDomainService.getAccessibleCourseAnnouncements(studentId, courseId);
		CourseWithCourseAnnouncementsViewModel c_a = new CourseWithCourseAnnouncementsViewModel();
		c_a.setCourseName(courseName);
		c_a.setCourseAnnouncements(courseAnnouncements.stream().map(c -> new CourseAnnouncementViewModel(c.getTimeStamp(), c.getMessage())).collect(Collectors.toList()));
		return c_a;
	}

	@Override
	public CourseWithCourseMaterialViewModel getCourseMaterial(String studentId, String courseId) throws StudentNotFoundException {
		String courseName = courseRepo.findOne(courseId).getCourseName();
		List<CourseMaterial> courseMaterials = courseAccessDomainService.getAccessibleCourseMaterials(studentId, courseId);
		CourseWithCourseMaterialViewModel c_m = new CourseWithCourseMaterialViewModel();
		c_m.setCourseName(courseName);
		c_m.setCourseMaterials(courseMaterials.stream().map(c -> new CourseMaterialViewModel(c.getName(), c.getTimestamp(), c.getFile())).collect(Collectors.toList()));
		return c_m;
	}

	
	@Override
	public List<CourseViewModel> getTeacherCourses(String teacherId) {
		
		return courseRepo.findAll().stream()
				.filter(c -> c.getTeacherId().equalsIgnoreCase(teacherId))
				.map(c ->{
					return new CourseViewModel(c.getCourseName(), c.getId(), c.getTeacherId());
				 })
				.collect(Collectors.toList());
	}
	

}
