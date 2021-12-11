package com.example.evaluation;

import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import com.example.evaluation.API.messaging.Channels;
import com.example.evaluation.application.service.Response;
import com.example.evaluation.application.service.ResponseStatus;
import com.example.evaluation.application.service.TaskService;
import com.example.evaluation.domain.model.Course;
import com.example.evaluation.domain.model.Task;
import com.example.evaluation.domain.model.TaskSubmission;
import com.example.evaluation.domain.repository.CourseRepository;
import com.example.evaluation.domain.repository.TaskRepository;
import com.example.evaluation.infrastructure.repository.CourseJpaRepository;

@SuppressWarnings("deprecation")
@SpringBootApplication
//@EnableBinding(Channels.class)
public class EvaluationApplication {

	private static final Logger log = LoggerFactory.getLogger(EvaluationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EvaluationApplication.class, args);
	}
	
	@Bean
	CommandLineRunner createCourse(CourseRepository courseRepo) {
		return (args) -> {
			courseRepo.save(new Course("Course 1", "Teacher", "Degree 1", List.of("Student 1", "Student 2")));
		};
	}
	
	@Bean
	CommandLineRunner testCreateTask(TaskService taskService, TaskRepository taskRepo) {
		return (args) -> {
			Response response1 = taskService.createTask("Course 1", "Task", LocalDateTime.parse("2020-12-01T13:30"), 0.2, "Teacher");
			log.info("= TestCreateTask =");
			
			log.info("Response 1: {} {}", response1.status, response1.message);
			Response response2 = taskService.createTask("Course 1", "Exam", LocalDateTime.parse("2020-12-01T13:30"), 0.8, "Teacher");
			log.info("Response 2: {} {}", response2.status, response2.message);
			
			List<Task> tasks = taskRepo.findByCourseId("Course 1");
			for(Task task : tasks) {
				log.info("{} {} {} {} {}", task.getCourseId(), task.getTaskId(), task.getDescription(), task.getDueDate().toString(), task.getWeight());
			}
		};
	}
	
	@Bean
	CommandLineRunner testJpaCollection(CourseJpaRepository cp) {
		return (args) -> {
			List<Course> courses = cp.findByStudentIds("Student 1");
			for(Course course : courses) {
				log.info("Course: {} {} {}", course.getCourseId(), course.getDegreeId(), course.getTeacherId());
			}
		};
	}
	/*
	@Bean
	CommandLineRunner testSubmitTask(TaskService taskService, TaskRepository taskRepo) {
		return (args) -> {
			log.info("= TestSubmitTask =");
			
			Response response = taskService.createTask("Course 2", "Task", LocalDateTime.parse("2025-12-01T13:30"), 1);
			log.info("CreateTask Response: {} {}", response.status, response.message);
			Task task = taskRepo.findByCourseId("Course 2").get(0);
			log.info("{} {} {} {} {}", task.getCourseId(), task.getTaskId(), task.getDescription(), task.getDueDate().toString(), task.getWeight());
			
			response = taskService.submitTask(task.getTaskId(), "Student 1", "Blablabla");
			log.info("SubmitTask Response: {} {}", response.status, response.message);
			TaskSubmission submission = taskRepo.findSubmissionsByTaskId(task.getTaskId()).get(0);
			log.info("Submission: {} {} {} {} {} {}", submission.getSubmissionId(), submission.getTaskId(), submission.getTaskId(), submission.getStudentId(), submission.getFile(), submission.getScore());
			
			response = taskService.assignScore(submission.getSubmissionId(), 10);
			log.info("AssignScore Response: {} {}", response.status, response.message);
			submission = taskRepo.findSubmissionsByTaskId(task.getTaskId()).get(0);
			log.info("AssignScore submission: {} {}", submission.getScore(), submission.passed());
			
			response = taskService.assignScore(submission.getSubmissionId(), 100);
			log.info("AssignInvalidScore Response: {} {}", response.status, response.message);
		};
	}
	*/
}
