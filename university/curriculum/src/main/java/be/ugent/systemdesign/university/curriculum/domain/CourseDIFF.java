package be.ugent.systemdesign.university.curriculum.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseDIFF {
	
	/*
	 * The front-end returns a list of courses that have to be changed. This domain service creates
	 * a DIFF based on this list. In principle, newCourses toggles changes in the oldCourses.
	 * 
	 * If newCourses contains a course that is not in oldCourses, then it gets added to the list.
	 * If newCourses contains a course that is in olCourses, then it gets removed.
	 * 
	 * Courses not listed in newCourses (because they are not changed in the front-end) are
	 * copied to the DIFF.
	 */
	public static List<Course> createDIFF(List<Course> oldCourses, List<Course> newCourses, Map<Course, FacultyCourseChangeType> changes) {
		List<Course> diff = new ArrayList<>(oldCourses);
		
		for (Course c : newCourses) {
			if (oldCourses.contains(c)) {
				diff.remove(c);
				changes.put(c, FacultyCourseChangeType.REMOVED);
			} else {
				diff.add(c);
				changes.put(c, FacultyCourseChangeType.ADDED);
			}
		}
		
		return diff;
	}
}
