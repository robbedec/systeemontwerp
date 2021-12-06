package be.ugent.timgeldof.learning_platform.domain.course;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMaterial {
	private Integer id;
	private LocalDateTime timestamp;
	private String name;
	private byte[] file;
	private boolean isVisible;
}
