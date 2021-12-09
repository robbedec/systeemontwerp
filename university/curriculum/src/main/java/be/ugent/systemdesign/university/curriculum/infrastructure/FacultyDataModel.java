package be.ugent.systemdesign.university.curriculum.infrastructure;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import be.ugent.systemdesign.university.curriculum.domain.Course;
import be.ugent.systemdesign.university.curriculum.domain.DegreeProgramme;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
public class FacultyDataModel {
	
	@Id
	private String facultyId;
	private String facultyName;
	private List<DegreeProgrammeDataModel> degrees;
	
	public FacultyDataModel(String _facultyId, String _facultyName, List<DegreeProgramme> _degrees) {
		this.facultyId = _facultyId;
		this.facultyName = _facultyName;
		this.degrees = _degrees.stream().map(d -> new DegreeProgrammeDataModel(d.getDegreeName(), d.getAvailableCourses())).collect(Collectors.toList());
	}
}
