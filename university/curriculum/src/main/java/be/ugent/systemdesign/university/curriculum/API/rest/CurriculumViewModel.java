package be.ugent.systemdesign.university.curriculum.API.rest;

import java.util.List;
import java.util.stream.Collectors;

import be.ugent.systemdesign.university.curriculum.application.query.CourseReadModel;
import be.ugent.systemdesign.university.curriculum.application.query.CurriculumReadModel;
import lombok.Getter;

@Getter
public class CurriculumViewModel {

	private String curriculumStatus;
	private String academicYear;
	private List<CourseViewModel> courses;
	
	public CurriculumViewModel(CurriculumReadModel _c) {
		this.curriculumStatus = _c.getCurriculumStatus();
		this.academicYear = _c.getAcademicYear().toString();
		this.courses = _c.getCourses().stream().map(co -> new CourseViewModel(co)).collect(Collectors.toList());
		
		/*
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		for (int i = 0; i < _c.getCourses().size(); ++i) {
			sb.append("{");
			sb.append("\"name\":\"");
			sb.append(_c.getCourses().get(i).getName());
			sb.append("\"}");
			
			if (i != _c.getCourses().size() - 1) {
				sb.append(",");
			}
		}
		
		sb.append("]");
		this.courses = sb.toString();
		*/
		
		
	}
}
