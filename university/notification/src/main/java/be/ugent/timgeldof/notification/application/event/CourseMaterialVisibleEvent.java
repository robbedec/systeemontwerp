package be.ugent.timgeldof.notification.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseMaterialVisibleEvent {
	public String studentId;
	public String courseName;
	public String fileName;
}
