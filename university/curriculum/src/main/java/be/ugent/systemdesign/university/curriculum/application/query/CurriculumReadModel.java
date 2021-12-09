package be.ugent.systemdesign.university.curriculum.application.query;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurriculumReadModel {
	
	private String curriculumId;
	private String curriculumStatus;
	private Integer academicYear;
	private List<CourseReadModel> courses;
	private String facultyName;
	private String degreeName;
	private String studentId;
}
