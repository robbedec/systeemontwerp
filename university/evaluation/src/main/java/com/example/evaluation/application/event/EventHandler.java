package com.example.evaluation.application.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaluation.application.service.CourseService;

@Service
public class EventHandler {
	@Autowired
	CourseService courseService;
	
	public void handleFacultyEvent(FacultyEvent event) {
		if(event.getChangeType().equals("REMOVED")) {
			courseService.removeCourse(event.getCourseId());
		} else {
			courseService.addCourse(event.getCourseId(), event.getCourseName(), event.getDegreeId(), event.getTeacherId());
		}
	}
	
	public void handleCurriculumChangedEvent(CurriculumChangedEvent event) {
		if(event.getChangeType().equals("REMOVED")) {
			courseService.removeStudent(event.getCourseId(), event.getStudentId());
		} else {
			courseService.addStudent(event.getCourseId(), event.getStudentId());
		}
	}
}
