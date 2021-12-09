package be.ugent.systemdesign.university.curriculum.infrastructure;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import be.ugent.systemdesign.university.curriculum.domain.Course;
import be.ugent.systemdesign.university.curriculum.domain.CurriculumStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
public class CurriculumDataModel {

	@Id
	private String curriculumId;
	
	private String studentId;
	private String curriculumStatus;
	private LocalDate dateCreated;
	private LocalDate dateLastChanged;
	private Integer academicYear;
	private List<CourseDataModel> courses;
	private String facultyName;
	private String degreeName;
	
	public CurriculumDataModel(String _curriculumId, String _studentId, CurriculumStatus _curriculumStatus, LocalDate _dateCreated, LocalDate _dateLastChanged, Year _academicYear, List<Course> _courses, String _facultyName, String _degreeName) {
		this.curriculumId = _curriculumId;
		this.studentId = _studentId;
		this.curriculumStatus = _curriculumStatus.name();
		this.dateCreated = _dateCreated;
		this.dateLastChanged = _dateLastChanged;
		this.academicYear = _academicYear.getValue();
		this.courses = _courses.stream().map(c -> new CourseDataModel(c.getName(), c.getCredits())).collect(Collectors.toList());
		this.facultyName = _facultyName;
		this.degreeName = _degreeName;
	}
}
