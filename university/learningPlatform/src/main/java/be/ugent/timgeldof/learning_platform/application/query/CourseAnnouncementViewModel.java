package be.ugent.timgeldof.learning_platform.application.query;

import java.time.LocalDateTime;
import java.util.List;

import be.ugent.timgeldof.learning_platform.domain.course.CourseAnnouncement;
import be.ugent.timgeldof.learning_platform.domain.course.CourseMaterial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseAnnouncementViewModel {
	LocalDateTime timestamp;
	String message;
}
