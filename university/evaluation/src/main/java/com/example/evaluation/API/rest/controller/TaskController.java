package com.example.evaluation.API.rest.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaluation.API.rest.post_model.AssignScorePostModel;
import com.example.evaluation.API.rest.post_model.TaskPostModel;
import com.example.evaluation.API.rest.post_model.TaskSubmissionPostModel;
import com.example.evaluation.API.rest.view_model.CourseViewModel;
import com.example.evaluation.API.rest.view_model.TaskSubmissionViewModel;
import com.example.evaluation.API.rest.view_model.TaskViewModel;
import com.example.evaluation.application.query.TaskQuery;
import com.example.evaluation.application.service.Response;
import com.example.evaluation.application.service.ResponseStatus;
import com.example.evaluation.application.service.TaskService;

import org.slf4j.Logger;

@RestController
@RequestMapping("api/evaluation/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskQuery taskQuery;

	@Autowired
	private TaskService taskService;

	@GetMapping
	public List<TaskViewModel> getTasks(String userId) {
		return taskQuery.getTasks(userId).stream().map(taskRM -> new TaskViewModel(taskRM))
				.collect(Collectors.toList());
	}

	@GetMapping("{taskId}/submission")
	public TaskSubmissionViewModel getTaskSubmission(@PathVariable String taskId, String studentId) {
		return new TaskSubmissionViewModel(taskQuery.getTaskSubmission(taskId, studentId));
	}

	@GetMapping("{taskId}/submissions")
	public List<TaskSubmissionViewModel> getTaskSubmissions(@PathVariable String taskId, String teacherId) {
		return taskQuery.getTaskSubmissions(taskId, teacherId).stream()
				.map(taskSubmissionRM -> new TaskSubmissionViewModel(taskSubmissionRM)).collect(Collectors.toList());
	}

	@GetMapping("/responsibilities")
	public List<CourseViewModel> getTaskResponsibilities(String teacherId) {
		return taskQuery.getTaskResponsibilities(teacherId);
	}

	@PostMapping
	public ResponseEntity<String> createTask(@RequestBody TaskPostModel task, String teacherId) {
		log.info("rw {}", task.getWeight());
		Response response = taskService.createTask(task.getCourseId(), task.getDescription(),
				LocalDateTime.parse(task.getDueDate()), (double) task.getWeight() / 100, teacherId);
		return createResponseEntity(response.status, "Task created", HttpStatus.CREATED, response.message,
				HttpStatus.CONFLICT);
	}

	@PostMapping("{taskId}/checkplagiarism")
	public ResponseEntity<String> checkPlagiarism(@PathVariable String taskId) {
		Response response = taskService.checkPlagiarism(taskId);
		return createResponseEntity(response.status, "Tasks checked for plagiarism", HttpStatus.OK,
				"Failed to check for plagiarism", HttpStatus.CONFLICT);
	}

	@PutMapping("{taskId}/submit")
	public ResponseEntity<String> submitTask(@RequestBody TaskSubmissionPostModel taskSubmission,
			@PathVariable String taskId, String studentId) {
		Response response = taskService.submitTask(taskId, studentId, taskSubmission.getFile());
		return createResponseEntity(response.status, "Task submitted", HttpStatus.OK, response.message,
				HttpStatus.CONFLICT);
	}

	@PutMapping("{taskId}/submissions/score")
	public ResponseEntity<String> assignScore(@RequestBody AssignScorePostModel score, @PathVariable String taskId,
			String teacherId) {
		log.info("{} {}", teacherId, score.getScore());
		Response response = taskService.assignScore(taskId, score.getStudentId(), score.getScore(), teacherId);
		return createResponseEntity(response.status, "Score assigned", HttpStatus.OK, response.message,
				HttpStatus.CONFLICT);
	}

	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String successMsg,
			HttpStatus successStatus, String failMsg, HttpStatus failStatus) {
		if (status == ResponseStatus.SUCCESS)
			return new ResponseEntity<>(successMsg, successStatus);
		return new ResponseEntity<>(failMsg, failStatus);
	}

}
