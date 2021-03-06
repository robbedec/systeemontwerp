package be.ugent.timgeldof.learning_platform.infrastructure.course;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.timgeldof.learning_platform.application.LearningPlatformServiceImpl;
import be.ugent.timgeldof.learning_platform.domain.course.Course;
import be.ugent.timgeldof.learning_platform.domain.course.CourseAnnouncement;
import be.ugent.timgeldof.learning_platform.domain.course.CourseMaterial;
import be.ugent.timgeldof.learning_platform.domain.course.CourseRepository;

// make repo injectable
@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository{

	private static final Logger log = LoggerFactory.getLogger(CourseRepositoryImpl.class);

	
	@Autowired
	CourseDataModelRepository repo;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Override
	public List<Course> findAll(){
		return mapCourseDataModelToDomainModel(repo.findAll());
	}

	@Override
	public Course findOne(String id) {
		Optional<CourseDataModel> c = repo.findById(id);
		if(c.isEmpty())
			throw new CourseNotFoundException();
		return mapCourseDataModelToDomainModel(c.get());
	}

	@Override
	public void save(Course c) {
		CourseDataModel c_dm = new CourseDataModel(
				c.getCourseName(), 
				c.getId(),
				c.getCourseCredits(),
				c.getTeacherId(),
				mapCourseAnnouncementDomainModelToDataModel(c.getCourseAnnouncements(), c),
				mapCourseMaterialDomainModelToDataModel(c.getCourseMaterial(), c)
		);
		c_dm = repo.save(c_dm);
		log.info("REPO HAS SAVED THE COURSE {} with ID {}", c_dm.getName(), c_dm.getId());
		c.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
		c.clearDomainEvents();
	}
	
	@Override
	public void remove(Course c) {
		Optional<CourseDataModel> c_dm = this.repo.findByNameAndCourseCredits(c.getCourseName(), c.getCourseCredits());
		if(c_dm.isPresent())
			this.repo.delete(c_dm.get());
		else 
			throw new CourseNotFoundException();
	}
	
	@Override
	public Course findByCourseNameAndCourseCredits(String courseName, Integer courseCredits) {
		Optional<CourseDataModel> c_dm = this.repo.findByNameAndCourseCredits(courseName, courseCredits);
		if(c_dm.isPresent())
			return(mapCourseDataModelToDomainModel(c_dm.get()));
		else 
			throw new CourseNotFoundException();	
	}
	
		/*
	 * Data model to domain model 
	 */
	public List<Course> mapCourseDataModelToDomainModel(List<CourseDataModel> list){
		List<Course> new_list = new ArrayList<>();
		if(list != null)
			list.forEach(c_dm -> {
				new_list.add(new Course(c_dm.getId(), c_dm.getName(),c_dm.getCourseCredits(), c_dm.getTeacherId(), mapCourseAnnouncementDataModelToDomainModel(c_dm.announcements), mapCourseMaterialDataModelToDomainModel(c_dm.coursematerials)));
			});
		return new_list;
	}
	
	public Course mapCourseDataModelToDomainModel(CourseDataModel c_dm){
		return new Course(c_dm.getId(), c_dm.getName(),c_dm.getCourseCredits(), c_dm.getTeacherId(),mapCourseAnnouncementDataModelToDomainModel(c_dm.announcements), mapCourseMaterialDataModelToDomainModel(c_dm.coursematerials));
	}
	
	public List<CourseMaterial> mapCourseMaterialDataModelToDomainModel(List<CourseMaterialDataModel> list){
		List<CourseMaterial> new_list = new ArrayList<>();
		if(list != null)
			list.forEach(cm_dm -> {
				new_list.add(new CourseMaterial(cm_dm.getId(), cm_dm.getTimestamp(), cm_dm.getName(), cm_dm.getFile(), cm_dm.isVisible()));
			});
		return new_list;
	}
	
	public List<CourseAnnouncement> mapCourseAnnouncementDataModelToDomainModel(List<CourseAnnouncementDataModel> list){
		List<CourseAnnouncement> new_list = new ArrayList<>();
		if(list != null)
			list.forEach(ca_dm -> {
				new_list.add(new CourseAnnouncement(ca_dm.getId(), ca_dm.getTimeStamp(), ca_dm.getMessage()));
			});
		return new_list;
	}
	
	/*
	 * Domain model to data model 
	 */
	
	public CourseDataModel mapCourseDomainModelToDataModel(Course c){
		CourseDataModel c_dm = new CourseDataModel();
		c_dm.setId(c.getId());
		c_dm.setName(c.getCourseName());
		return c_dm;
	}

	public List<CourseMaterialDataModel> mapCourseMaterialDomainModelToDataModel(List<CourseMaterial> list, Course course){
		List<CourseMaterialDataModel> new_list = new ArrayList<>();
		if(list != null)
			list.forEach(cm -> {
				new_list.add(new CourseMaterialDataModel(cm.getId(), mapCourseDomainModelToDataModel(course), cm.getName(), cm.getTimestamp(), cm.getFile(), cm.isVisible()));
			});
		return new_list;
	}
	
	public List<CourseAnnouncementDataModel> mapCourseAnnouncementDomainModelToDataModel(List<CourseAnnouncement> list, Course course){
		List<CourseAnnouncementDataModel> new_list = new ArrayList<>();
		if(list != null)
			list.forEach(ca -> {
				new_list.add(new CourseAnnouncementDataModel(ca.getId(), ca.getTimeStamp(), ca.getMessage(), mapCourseDomainModelToDataModel(course)));
			});
		return new_list;
	}




}
