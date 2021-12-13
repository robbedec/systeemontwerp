package be.ugent.timgeldof.learning_platform.domain.course_access;

import java.time.LocalDateTime;
import java.util.List;

import be.ugent.timgeldof.learning_platform.domain.seedwork.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseAccess extends AggregateRoot {
	private Integer studentId;
	private List<Integer> courseIds;
	private boolean undergoingPlagiarismProcedure;
	private boolean invoiceOpen;
	
	public void addCourse(int courseId) {
		if(!courseIds.contains(courseId))
			courseIds.add(courseId);
	}
	public void removeCourse(int courseId) {
		if(courseIds.contains(courseId))
			courseIds.remove(courseId);
	}
	
	public boolean isStudentAllowedCourseAccess() {
		if(undergoingPlagiarismProcedure || invoiceOpen)
			return false;
		else
			return true;
	}
}
