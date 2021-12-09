package be.ugent.timgeldof.learning_platform.infrastructure.course;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.ugent.timgeldof.learning_platform.domain.course.CourseAnnouncement;
import be.ugent.timgeldof.learning_platform.domain.course.CourseMaterial;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course_materials")
@Entity
public class CourseMaterialDataModel {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer Id;
	@ManyToOne
	@JoinColumn(name="courseId")
	private CourseDataModel course;
	private String name;
	private LocalDateTime timestamp;
    @Lob
	private byte[] file; 
	@Getter
	private boolean isVisible;
}
