package com.example.evaluation.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.evaluation.domain.model.Course;

@Repository
public interface CourseJpaRepository extends JpaRepository<Course, String>{
	Optional<Course> findById(String courseId);
	List<Course> findByDegreeId(String degreeId);
	List<Course> findByStudentIds(String studentId);
}
