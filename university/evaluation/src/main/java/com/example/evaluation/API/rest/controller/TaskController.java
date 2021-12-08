package com.example.evaluation.API.rest.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

import com.example.evaluation.API.rest.view_model.TaskSubmissionViewModel;
import com.example.evaluation.API.rest.view_model.TaskViewModel;
import com.example.evaluation.application.Response;
import com.example.evaluation.application.ResponseStatus;
import com.example.evaluation.application.TaskService;
import com.example.evaluation.application.query.TaskQuery;

@RestController
@RequestMapping("api/evaluation/tasks/")
@CrossOrigin(origins="*")
public class TaskController {
	@Autowired
	private TaskQuery taskQuery;
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping
	public ResponseEntity<String> createTask(@RequestBody TaskViewModel taskVM) {
		Response response = taskService.createTask(taskVM.courseId, taskVM.description, LocalDate.parse(taskVM.dueDate));
		return createResponseEntity(response.status, "Task created", HttpStatus.CREATED, response.message, HttpStatus.CONFLICT);
	}
	
	@PostMapping("submission")
	public ResponseEntity<String> submitTask(@RequestBody TaskSubmissionViewModel taskSubmissionVM) {
		Response response = taskService.submitTask(taskSubmissionVM.taskId, taskSubmissionVM.studentId, taskSubmissionVM.file);
		return createResponseEntity(response.status, "Task submitted", HttpStatus.CREATED, response.message, HttpStatus.CONFLICT);
	}
	
	@PutMapping("submissions/{id}/score")
	public ResponseEntity<String> assignScore(@PathVariable String taskSubmissionId, int score) {
		Response response = taskService.assignScore(taskSubmissionId, score);
		return createResponseEntity(response.status, "Score assigned", HttpStatus.OK, response.message, HttpStatus.CONFLICT);
	}
	
	@GetMapping("{id}/submissions")
	public List<TaskSubmissionViewModel> getTaskSubmissions(@PathVariable String taskId) {
		return taskQuery.getTaskSubmissions(taskId).stream().map(taskSubmissionRM -> new TaskSubmissionViewModel(taskSubmissionRM)).collect(Collectors.toList());
	}
	
	private ResponseEntity<String> createResponseEntity(ResponseStatus status, String successMsg, HttpStatus successStatus, String failMsg, HttpStatus failStatus) {
		if(status == ResponseStatus.SUCCESS)
			return new ResponseEntity<>(successMsg, successStatus);
		return new ResponseEntity<>(failMsg, failStatus);
	}
}