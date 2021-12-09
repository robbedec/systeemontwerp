package be.ugent.systemdesign.university.curriculum.API.rest;

import java.util.List;
import java.util.stream.Collectors;

import be.ugent.systemdesign.university.curriculum.application.query.CourseReadModel;
import be.ugent.systemdesign.university.curriculum.application.query.CurriculumReadModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurriculumViewModel {

	private String curriculumId;
	private String curriculumStatus;
	private String academicYear;
	private List<CourseViewModel> courses;
	private String facultyName;
	private String degreeName;
	private String studentId;
	
	public CurriculumViewModel(CurriculumReadModel _c) {
		this.curriculumId = _c.getCurriculumId();
		this.curriculumStatus = _c.getCurriculumStatus();
		this.academicYear = _c.getAcademicYear().toString();
		this.courses = _c.getCourses().stream().map(co -> new CourseViewModel(co)).collect(Collectors.toList());
		this.facultyName = _c.getFacultyName();
		this.degreeName = _c.getDegreeName();
		this.studentId = _c.getStudentId();
	}
}
