package be.ugent.timgeldof.learning_platform.infrastructure.course;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_announcements")
public class CourseAnnouncementDataModel {
	@javax.persistence.Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer Id;
	private LocalDateTime timeStamp;
	private String message;	
	@ManyToOne
	@JoinColumn(name="courseId")
	private CourseDataModel course;
}
