package be.ugent.systemdesign.university.curriculum.domain;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import be.ugent.systemdesign.university.curriculum.domain.seedwork.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Faculty {

	private String facultyId;
	private String facultyName;
	private List<DegreeProgramme> degrees;
	
	public Faculty(String facultyName) {
		this.facultyName = facultyName;
		this.degrees = new ArrayList<>();
	}
	
	public void addDegree(String _name) {
		this.degrees.add(DegreeProgramme.builder()
				.degreeName(_name)
				.build()
			);
	}
}
