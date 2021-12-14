package com.example.evaluation.application.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaluation.domain.model.Course;
import com.example.evaluation.domain.repository.CourseRepository;
import com.example.evaluation.infrastructure.exception.CourseNotFoundException;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseRepository courseRepo;

	@Override
	public Response addCourse(String courseId, String courseName, String teacherId, String degreeId) {
		try {
			Course course = new Course(courseId, courseName, teacherId, degreeId, new ArrayList<>());
			courseRepo.save(course);
			return new Response(ResponseStatus.SUCCESS, "Course added");
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Failed");
		}
	}
	
	@Override
	public Response removeCourse(String courseId) {
		try {
			courseRepo.remove(courseId);
			return new Response(ResponseStatus.SUCCESS, "Course removed");
		} catch(Exception e) {
			return new Response(ResponseStatus.FAIL, "Failed");
		}
	}

	@Override
	public Response addStudent(String courseId, String studentId) {
		try {
			courseRepo.addStudent(courseId, studentId);
			return new Response(ResponseStatus.SUCCESS, "Student added to course");
		} catch (CourseNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Course doesn't exist");
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Failed");
		}
	}

	public Response removeStudent(String courseId, String studentId) {
		try {
			courseRepo.addStudent(courseId, studentId);
			return new Response(ResponseStatus.SUCCESS, "Student removed from course");
		} catch (CourseNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Course doesn't exist");
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Failed");
		}
	}

}
