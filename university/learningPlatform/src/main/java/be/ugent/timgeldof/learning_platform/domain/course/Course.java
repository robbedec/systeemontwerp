package be.ugent.timgeldof.learning_platform.domain.course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Id;

import be.ugent.timgeldof.learning_platform.domain.seedwork.AggregateRoot;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends AggregateRoot{
	@Id
	private Integer id;
	private String courseName;
	private Integer courseCredits;
	private Integer teacherId;
	
	private List<CourseAnnouncement> courseAnnouncements;
	private List<CourseMaterial> courseMaterial;
	
	@Override
	public String toString() {
		return courseName;
	}
	
	public List<CourseMaterial> getVisibleCourseMaterials(){
		return this.courseMaterial.stream().filter(cm -> cm.isVisible()).collect(Collectors.toList());
	}
	
	public void addCourseMaterial(CourseMaterial cm) {
		if(this.courseMaterial == null)
			this.courseMaterial = new ArrayList<>();
		
		this.courseMaterial.add(cm);
	}
	
	public void addCourseAnnouncement(CourseAnnouncement ca) {
		if(this.courseAnnouncements == null)
			this.courseAnnouncements = new ArrayList<>();
		
		this.courseAnnouncements.add(ca);
		this.addDomainEvent(new AddedCourseAnnouncementDomainEvent(this.courseName, ca.getMessage()));
	}
	
	public void changeCourseMaterialVisibility(boolean isVisible) {
		courseMaterial.forEach(cm -> {
			/*
			 * for each material that was not visible before and is made visible just now,
			 * we will publish a domain event
			 */ 
			if(!cm.isVisible() && isVisible)
				this.addDomainEvent(new PublishedCourseMaterialVisibleDomainEvent(this.courseName, cm.getName()));
			cm.setVisible(isVisible);
		});
	}
	
}
