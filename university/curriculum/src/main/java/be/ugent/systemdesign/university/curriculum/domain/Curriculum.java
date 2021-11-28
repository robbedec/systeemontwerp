package be.ugent.systemdesign.university.curriculum.domain;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import be.ugent.systemdesign.university.curriculum.domain.seedwork.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Curriculum extends AggregateRoot {
	
	private String curriculumId;
	private Integer studentId;
	private CurriculumStatus curriculumStatus;
	private LocalDate dateCreated;
	private LocalDate dateLastChanged;
	private Year academicYear;
	private List<Course> courses;
	
	public Curriculum() {
		curriculumStatus = CurriculumStatus.PROVISIONAL;
		dateCreated = LocalDate.now();
		dateLastChanged = dateCreated;
		courses = new ArrayList<>();
		academicYear = Year.now();
	}
	
	public void addCourse(String _name, Integer _credits) {
		this.courses.add(Course.builder()
			.name(_name)
			.credits(_credits)
			.build()
		);
	}
	
	public void markCurriculumAsProposed() {
		this.curriculumStatus = CurriculumStatus.PROPOSED;
	}
}
