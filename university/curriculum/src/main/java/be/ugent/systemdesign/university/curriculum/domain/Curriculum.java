package be.ugent.systemdesign.university.curriculum.domain;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import be.ugent.systemdesign.university.curriculum.domain.exception.CurriculumChangesByStudentDisabledException;
import be.ugent.systemdesign.university.curriculum.domain.exception.OnlyProvisionOrRejectedCurriculumCanBeProposedException;
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
	private String studentId;
	private CurriculumStatus curriculumStatus;
	private LocalDate dateCreated;
	private LocalDate dateLastChanged;
	private Year academicYear;
	private List<Course> courses;
	private String facultyName;
	
	public Curriculum(String studentId, String facultyName) {
		this.studentId = studentId;
		this.curriculumStatus = CurriculumStatus.PROVISIONAL;
		this.dateCreated = LocalDate.now();
		this.dateLastChanged = dateCreated;
		this.courses = new ArrayList<>();
		this.academicYear = Year.now();
		this.facultyName = facultyName;
	}
	
	public void addCourse(String _name, Integer _credits) {
		this.courses.add(Course.builder()
			.name(_name)
			.credits(_credits)
			.build()
		);
	}
	
	public void markCurriculumAsProposed() {
		if (this.curriculumStatus == CurriculumStatus.PROPOSED || this.curriculumStatus == CurriculumStatus.ACCEPTED) {
			throw new OnlyProvisionOrRejectedCurriculumCanBeProposedException();
		}
		
		this.curriculumStatus = CurriculumStatus.PROPOSED;
	}
	
	public void changeCurriculum(List<Course> _courses, boolean changedByStudent, Map<Course, FacultyCourseChangeType> changes) {
		if ((this.curriculumStatus == CurriculumStatus.PROPOSED || this.curriculumStatus == CurriculumStatus.ACCEPTED) && changedByStudent) {
			throw new CurriculumChangesByStudentDisabledException();
		}
		
		changes.entrySet().stream().forEach(entry -> {
			addDomainEvent(new CurriculumChangedDomainEvent(
					this.studentId,
					entry.getKey().getName(),
					entry.getKey().getCredits(),
					entry.getValue()
				)
			);
		});
		
		this.courses = _courses;
	}
}
