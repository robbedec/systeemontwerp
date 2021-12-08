package be.ugent.timgeldof.learning_platform.application.query;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseWithCourseAnnouncementsViewModel {
	private String courseName;
	private List<CourseAnnouncementViewModel> courseAnnouncements;
}
