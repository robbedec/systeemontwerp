package be.ugent.systemdesign.university.curriculum.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import be.ugent.systemdesign.university.curriculum.domain.exception.DuplicateCourseException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DegreeProgramme {
	
	private String degreeName;
	private List<Course> availableCourses;
	
	public DegreeProgramme(String degreeName) {
		this.degreeName = degreeName;
		this.availableCourses = new ArrayList<>();
	}
	
	public void addCourse(String _name, Integer _credits) throws DuplicateCourseException {
		
		boolean isPresent = this.availableCourses.stream().anyMatch(x -> x.getName().equals(_name) && x.getCredits() == _credits);
		
		if (isPresent) {
			throw new DuplicateCourseException();
		} else {
			this.availableCourses.add(new Course(_name, _credits));
		}
	}
	
	public void removeCourse(String _name, Integer _credits) {
		this.availableCourses.remove(new Course(_name, _credits));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		return this.degreeName.equals(((DegreeProgramme) obj).getDegreeName());
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(this.degreeName);
	}
	
}
