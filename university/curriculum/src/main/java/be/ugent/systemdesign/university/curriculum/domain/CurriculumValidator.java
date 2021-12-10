package be.ugent.systemdesign.university.curriculum.domain;

import java.util.HashSet;
import java.util.List;

import be.ugent.systemdesign.university.curriculum.domain.exception.CurriculumInvalidException;

public class CurriculumValidator {

	public static boolean validateCurriculum(List<Course> facultyCourses, List<Course> curriculumCourses) {
		
		// Check if courses received from the front-end actually exist in the faculty database
		if (!facultyCourses.containsAll(curriculumCourses)) throw new CurriculumInvalidException();
		
		// Check for duplicates in curriculumCourses
		if((new HashSet<Course>(curriculumCourses)).size() < curriculumCourses.size()) throw new CurriculumInvalidException();
		
		return true;
	}
}
