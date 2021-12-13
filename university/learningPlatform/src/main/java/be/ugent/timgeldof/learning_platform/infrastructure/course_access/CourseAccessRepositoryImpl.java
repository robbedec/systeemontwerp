package be.ugent.timgeldof.learning_platform.infrastructure.course_access;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import be.ugent.timgeldof.learning_platform.domain.course.Course;
import be.ugent.timgeldof.learning_platform.domain.course.CourseAnnouncement;
import be.ugent.timgeldof.learning_platform.domain.course.CourseMaterial;
import be.ugent.timgeldof.learning_platform.domain.course.CourseRepository;
import be.ugent.timgeldof.learning_platform.domain.course_access.CourseAccess;
import be.ugent.timgeldof.learning_platform.domain.course_access.CourseAccessRepository;
import be.ugent.timgeldof.learning_platform.domain.course_access.StudentNotFoundException;
import be.ugent.timgeldof.learning_platform.infrastructure.course.CourseDataModelRepository;

// make repo injectable
@Repository
@Transactional
public class CourseAccessRepositoryImpl implements CourseAccessRepository{

	@Autowired
	CourseAccessJPARepository repo;
	
	@Override
	public List<CourseAccess> findAll() {
		return mapCourseAccessDataModelToDomainModel(repo.findAll());
	}

	@Override
	public CourseAccess findById(Integer id) throws StudentNotFoundException {
		Optional<CourseAccessDataModel> ca_o = repo.findById(id);
		if(ca_o.isEmpty()) {
			throw new StudentNotFoundException();
		}
		return mapCourseAccessDataModelToDomainModel(ca_o.get());
	}

	@Override
	public void save(CourseAccess c) {
		repo.save(mapCourseAccessDomainModelToDataModel(c));
	}
	
	public List<CourseAccess> mapCourseAccessDataModelToDomainModel(List<CourseAccessDataModel> list){
		List<CourseAccess> newList = new ArrayList<>();
		list.forEach(ca_dm -> {
			newList.add(new CourseAccess(ca_dm.getStudentId(), ca_dm.getCourseIds(), ca_dm.isUndergoingPlagiarismProcedure(), ca_dm.isInvoiceOpen()));
		});
		
		return newList;
	}
	public CourseAccess mapCourseAccessDataModelToDomainModel(CourseAccessDataModel ca_dm) {
		return new CourseAccess(ca_dm.getStudentId(), ca_dm.getCourseIds(), ca_dm.isUndergoingPlagiarismProcedure(), ca_dm.isInvoiceOpen());
	}
	
	public CourseAccessDataModel mapCourseAccessDomainModelToDataModel(CourseAccess ca) {
		return new CourseAccessDataModel(ca.getStudentId(), ca.getCourseIds(), ca.isUndergoingPlagiarismProcedure(), ca.isInvoiceOpen());
	}

	@Override
	public void removeCourse(Course c) {
		List<CourseAccessDataModel> ca_list = repo.findAll();
		ca_list = ca_list.stream().map(ca -> {
			ca.getCourseIds().remove(c.getId());
			return ca;
		}).collect(Collectors.toList());
		
		this.repo.saveAll(ca_list);
	}


}
