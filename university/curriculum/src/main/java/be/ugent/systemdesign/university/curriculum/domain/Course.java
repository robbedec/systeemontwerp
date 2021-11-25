package be.ugent.systemdesign.university.curriculum.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
public class Course {
	
	private String name;
	private Integer credits;
}
