package be.ugent.timgeldof.learning_platform.domain.course;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseAnnouncement {
	private Integer Id;
	private LocalDateTime timeStamp;
	private String message;
}
