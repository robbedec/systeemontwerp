package com.example.evaluation.infrastructure.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.evaluation.domain.model.Course;
import com.example.evaluation.domain.repository.CourseRepository;
import com.example.evaluation.infrastructure.exception.CourseNotFoundException;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

	@Autowired
	CourseJpaRepository courseJpaRepo;
	
	@Override
	public void save(Course course) {
		courseJpaRepo.save(course);
	}
	
	@Override
	public String findTeacherForCourse(String courseId) {
		Course course = courseJpaRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);
		return course.getTeacherId();
	}

	public List<String> findStudentsFollowingDegree(String degreeId) {
		Set<String> studentIds = new HashSet<>();
		List<Course> courses = courseJpaRepo.findByDegreeId(degreeId);
		for(Course course : courses) {
			studentIds.addAll(course.getStudentIds());
		}
		return List.copyOf(studentIds);
	}

	@Override
	public List<String> findStudentsFollowingCourse(String courseId) {
		Course course = courseJpaRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);
		return course.getStudentIds();
	}

	@Override
	public List<String> findCoursesInDegree(String degreeId) {
		return courseJpaRepo.findByDegreeId(degreeId).stream().map(course -> course.getCourseId()).collect(Collectors.toList());
	}

	@Override
	public List<String> findCoursesStudentFollows(String studentId) {
		return courseJpaRepo.findByStudentIds(studentId).stream().map(course -> course.getCourseId()).collect(Collectors.toList());
	}

}
