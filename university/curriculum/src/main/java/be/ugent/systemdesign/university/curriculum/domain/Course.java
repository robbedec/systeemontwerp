package be.ugent.systemdesign.university.curriculum.domain;

import java.util.Objects;

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
	
	public boolean equals(Course obj) {
		if (obj == null) return false;
		return this.name == obj.getName() && this.credits == obj.getCredits();
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(this.name, this.credits);
	}
}
