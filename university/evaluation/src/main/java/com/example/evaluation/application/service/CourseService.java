package com.example.evaluation.application.service;

public interface CourseService {
	public Response addCourse(String courseId, String courseName, String degreeId, String teacherId);
	public Response removeCourse(String courseId);
	public Response addStudent(String courseId, String studentId);
	public Response removeStudent(String courseId, String studentId);
}
