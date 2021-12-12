package be.ugent.systemdesign.university.curriculum.domain;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import be.ugent.systemdesign.university.curriculum.domain.exception.CurriculumChangesByStudentDisabledException;
import be.ugent.systemdesign.university.curriculum.domain.exception.CurriculumExceedsMaximumCreditsException;
import be.ugent.systemdesign.university.curriculum.domain.exception.CurriculumLockedException;
import be.ugent.systemdesign.university.curriculum.domain.exception.OnlyProposedCurriculumCanBeReviewedException;
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
	private String degreeName;
	private List<Course> completedCourses;
	
	public Curriculum(String studentId, String facultyName, String degreeName) {
		this.studentId = studentId;
		this.curriculumStatus = CurriculumStatus.PROVISIONAL;
		this.dateCreated = LocalDate.now();
		this.dateLastChanged = dateCreated;
		this.courses = new ArrayList<>();
		this.academicYear = Year.now();
		this.facultyName = facultyName;
		this.degreeName = degreeName;
	}
	
	public void addCourse(String _name, Integer _credits) {
		this.courses.add(Course.builder()
			.name(_name)
			.credits(_credits)
			.build()
		);
	}
	
	public void markCurriculumAsProposed() throws OnlyProvisionOrRejectedCurriculumCanBeProposedException {
		if (this.curriculumStatus == CurriculumStatus.PROPOSED || this.curriculumStatus == CurriculumStatus.ACCEPTED) {
			throw new OnlyProvisionOrRejectedCurriculumCanBeProposedException();
		}
		
		this.curriculumStatus = CurriculumStatus.PROPOSED;
	}
	
	public void reviewCurriculum(CurriculumStatus status, boolean calledByStudent) throws IllegalAccessException, OnlyProposedCurriculumCanBeReviewedException {
		// A student can not review a curriculum
		if (calledByStudent) {
			throw new IllegalAccessException();
		}
		
		if (this.curriculumStatus != CurriculumStatus.PROPOSED) {
			throw new OnlyProposedCurriculumCanBeReviewedException();
		}
		
		addDomainEvent(new CurriculumReviewedDomainEvent(this.studentId, status));
		this.curriculumStatus = status;
	}
	
	public void changeCurriculum(List<Course> _courses, boolean changedByStudent, Map<Course, FacultyCourseChangeType> changes) throws CurriculumExceedsMaximumCreditsException, CurriculumChangesByStudentDisabledException, CurriculumLockedException {
		
		if (this.curriculumStatus == CurriculumStatus.REJECTED) this.curriculumStatus = CurriculumStatus.PROVISIONAL;
		
		// A curriculum can not be changed by a student after it has been proposed or accepted
		// Changes can still be made by university personnel (i.e. program counselor)
		if ((this.curriculumStatus == CurriculumStatus.PROPOSED || this.curriculumStatus == CurriculumStatus.ACCEPTED) && changedByStudent) {
			throw new CurriculumChangesByStudentDisabledException();
		}
		
		// Credits of the curriculum should not exceed 60
		if (_courses.stream().mapToInt(Course::getCredits).sum() > 60) {
			throw new CurriculumExceedsMaximumCreditsException();
		}
		
		// The student is allowed to make changes until three months after its initial creation date (start of the academic year)
		// Program counselors are allowed to make changes for an additional month, for unforeseen events 
		if ((this.dateCreated.plusMonths(2).isBefore(LocalDate.now()) && changedByStudent) || this.dateCreated.plusMonths(3).isBefore(LocalDate.now())) {
			throw new CurriculumLockedException();
		}
		
		changes.entrySet().stream().forEach(entry -> {
			addDomainEvent(new CurriculumChangedDomainEvent(
					this.studentId,
					entry.getKey().getCourseId(),
					entry.getKey().getName(),
					entry.getKey().getCredits(),
					entry.getValue()
				)
			);
		});
		
		this.courses = _courses;
		this.dateLastChanged = LocalDate.now();
	}
}
