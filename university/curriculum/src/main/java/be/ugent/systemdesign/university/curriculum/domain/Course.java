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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		return this.name.equals(((Course) obj).getName()) && this.credits == ((Course) obj).getCredits();
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(this.name, this.credits);
	}
}
