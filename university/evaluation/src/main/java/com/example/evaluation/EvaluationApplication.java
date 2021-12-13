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
import com.example.evaluation.API.rest.view_model.ScoreCardViewModel;
import com.example.evaluation.application.query.ScoreCardQuery;
import com.example.evaluation.application.query.TaskQuery;
import com.example.evaluation.application.query.read_model.CourseScoreReadModel;
import com.example.evaluation.application.query.read_model.ScoreCardReadModel;
import com.example.evaluation.application.query.read_model.TaskReadModel;
import com.example.evaluation.application.service.Response;
import com.example.evaluation.application.service.ResponseStatus;
import com.example.evaluation.application.service.ScoreCardService;
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
	CommandLineRunner startTest(CourseRepository courseRepo, TaskService taskService,
			ScoreCardService scoreCardService) {
		return (args) -> {
			courseRepo.save(new Course("Course1", "Teacher1", "Degree1", List.of("Student1", "Student2")));
			courseRepo.save(new Course("Course2", "Teacher1", "Degree1", List.of("Student1", "Student2")));

			Response res = taskService.createTask("Course1", "Course1 Task", LocalDateTime.parse("2022-01-01T12:00"),
					0.2, "Teacher1");
			String taskId1 = res.message.split(" ")[1];
			res = taskService.createTask("Course1", "Course1 Task", LocalDateTime.parse("2022-01-01T12:00"), 0.8,
					"Teacher1");
			String taskId2 = res.message.split(" ")[1];
			res = taskService.createTask("Course2", "Course2 Exam", LocalDateTime.parse("2022-01-01T12:00"), 0.8,
					"Teacher1");
			String taskId3 = res.message.split(" ")[1];

			taskService.assignScore(taskId1, "Student1", 15, "Teacher1");
			taskService.assignScore(taskId2, "Student1", 10, "Teacher1");
			taskService.assignScore(taskId3, "Student1", 13, "Teacher1");
			taskService.assignScore(taskId1, "Student2", 10, "Teacher1");
			taskService.assignScore(taskId2, "Student2", 10, "Teacher1");
			taskService.assignScore(taskId3, "Student2", 8, "Teacher1");

			scoreCardService.generateScoreCards();
		};
	}

	/*
	 * @Bean CommandLineRunner testCourse(CourseRepository courseRepo) { return
	 * (args) -> { log.info("= TestCourse ="); courseRepo.save(new Course("Course1",
	 * "Teacher1", "Degree1", List.of("Student1", "Student2"))); courseRepo.save(new
	 * Course("Course2", "Teacher2", "Degree1", List.of("Student1", "Student2")));
	 * courseRepo.save(new Course("Course3", "Teacher1", "Degree1",
	 * List.of("Student1")));
	 * 
	 * log.info("Courses degree1"); List<String> courses =
	 * courseRepo.findCoursesInDegree("Degree1"); for(String course : courses) {
	 * log.info("{}", course); }
	 * 
	 * log.info("Courses student1"); courses =
	 * courseRepo.findCoursesStudentFollows("Student1"); for(String course :
	 * courses) { log.info("{}", course); }
	 * 
	 * log.info("Courses student2"); courses =
	 * courseRepo.findCoursesStudentFollows("Student2"); for(String course :
	 * courses) { log.info("{}", course); } }; }
	 * 
	 * @Bean CommandLineRunner testCreateTask(TaskService taskService,
	 * TaskRepository taskRepo) { return (args) -> { log.info("= TestCreateTask =");
	 * Response response1 = taskService.createTask("Course1", "Task",
	 * LocalDateTime.parse("2023-12-01T13:30"), 0.2, "Teacher1");
	 * log.info("Response1: {} {}", response1.status, response1.message); Response
	 * response2 = taskService.createTask("Course1", "Exam",
	 * LocalDateTime.parse("2023-12-01T13:30"), 0.8, "Teacher1");
	 * log.info("Response2: {} {}", response2.status, response2.message); Response
	 * response3 = taskService.createTask("Course1", "Task",
	 * LocalDateTime.parse("2020-12-01T13:30"), 0.1, "Teacher2");
	 * log.info("Response3 (fail): {} {}", response3.status, response3.message);
	 * 
	 * List<Task> tasks = taskRepo.findByCourseId("Course1"); for(Task task : tasks)
	 * { log.info("{} {} {} {} {}", task.getCourseId(), task.getTaskId(),
	 * task.getDescription(), task.getDueDate().toString(), task.getWeight()); } };
	 * }
	 * 
	 * @Bean CommandLineRunner testJpaCollection(CourseJpaRepository cp) { return
	 * (args) -> { log.info("= Find student courses ="); List<Course> courses =
	 * cp.findByStudentIds("Student1"); for(Course course : courses) {
	 * log.info("Course: {} {} {}", course.getCourseId(), course.getDegreeId(),
	 * course.getTeacherId()); } }; }
	 * 
	 * @Bean CommandLineRunner testTaskQuery(TaskQuery tq) { return (args) -> {
	 * log.info("= Student tasks ="); List<TaskReadModel> tasks =
	 * tq.getTasks("Student1"); for(TaskReadModel task : tasks) {
	 * log.info("{} {} {} {} {}", task.taskId, task.courseId, task.description,
	 * task.dueDate.toString(), task.weight); } }; }
	 * 
	 * @Bean CommandLineRunner generateScoreCards(ScoreCardService scs) { return
	 * (args) -> { scs.generateScoreCards(); }; }
	 */
}
