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
	public void remove(String courseId) {
		courseJpaRepo.deleteById(courseId);
	}

	@Override
	public void addStudent(String courseId, String studentId) {
		Course course = courseJpaRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);
		course.getStudentIds().add(studentId);
		courseJpaRepo.save(course);
	}

	@Override
	public void removeStudent(String courseId, String studentId) {
		Course course = courseJpaRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);
		course.getStudentIds().remove(studentId);
		courseJpaRepo.save(course);
	}

	@Override
	public String findTeacherForCourse(String courseId) {
		Course course = courseJpaRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);
		return course.getTeacherId();
	}

	@Override
	public String findCourseName(String courseId) {
		Course course = courseJpaRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);
		return course.getCourseName();
	}

	public List<String> findStudentsFollowingDegree(String degreeId) {
		Set<String> studentIds = new HashSet<>();
		List<Course> courses = courseJpaRepo.findByDegreeId(degreeId);
		for (Course course : courses) {
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
		return courseJpaRepo.findByDegreeId(degreeId).stream().map(course -> course.getCourseId())
				.collect(Collectors.toList());
	}

	@Override
	public List<String> findCoursesStudentFollows(String studentId) {
		return courseJpaRepo.findByStudentIds(studentId).stream().map(course -> course.getCourseId())
				.collect(Collectors.toList());
	}

	@Override
	public List<String> findDegreesStudentFollows(String studentId) {
		return List.copyOf(courseJpaRepo.findByStudentIds(studentId).stream().map(course -> course.getDegreeId())
				.collect(Collectors.toSet()));
	}

	@Override
	public List<String> findStudents() {
		Set<String> students = new HashSet<>();
		for (Course course : courseJpaRepo.findAll()) {
			students.addAll(course.getStudentIds());
		}
		return List.copyOf(students);
	}

	@Override
	public List<String> findCoursesStudentFollowsInDegree(String degreeId, String studentId) {
		return courseJpaRepo.findByDegreeIdAndStudentIds(degreeId, studentId).stream()
				.map(course -> course.getCourseId()).collect(Collectors.toList());
	}

}
