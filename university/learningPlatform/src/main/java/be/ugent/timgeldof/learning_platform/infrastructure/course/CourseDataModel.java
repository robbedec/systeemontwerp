package be.ugent.timgeldof.learning_platform.infrastructure.course;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "courses")
public class CourseDataModel {
	private String name;
	@Id
	private String Id;
	private Integer courseCredits;
	private String teacherId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<CourseAnnouncementDataModel> announcements;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<CourseMaterialDataModel> coursematerials;
	
}
