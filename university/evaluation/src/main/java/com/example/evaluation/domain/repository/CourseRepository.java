package com.example.evaluation.domain.repository;

import java.util.List;

import com.example.evaluation.domain.model.Course;

public interface CourseRepository {

	void save(Course course);

	void remove(String courseId);

	void addStudent(String courseId, String studentId);

	void removeStudent(String courseId, String studentId);

	String findTeacherForCourse(String courseId);

	String findCourseName(String courseId);

	List<String> findStudentsFollowingDegree(String degreeId);

	List<String> findStudentsFollowingCourse(String courseId);

	List<String> findCoursesInDegree(String degreeId);

	List<String> findCoursesStudentFollows(String studentId);

	List<String> findDegreesStudentFollows(String studentId);

	List<String> findCoursesStudentFollowsInDegree(String degreeId, String studentId);

	List<String> findStudents();

}
