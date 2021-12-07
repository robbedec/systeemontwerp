package com.example.evaluation.application;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaluation.domain.exception.InvalidScoreException;
import com.example.evaluation.domain.model.Task;
import com.example.evaluation.domain.model.TaskSubmission;
import com.example.evaluation.domain.repository.TaskRepository;
import com.example.evaluation.domain.repository.TaskSubmissionRepository;
import com.example.evaluation.infrastructure.exception.TaskNotFoundException;
import com.example.evaluation.infrastructure.exception.TaskSubmissionNotFoundException;

@Transactional
@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	TaskRepository taskRepo;
	
	@Autowired
	TaskSubmissionRepository taskSubmissionRepo;

	@Override
	public Response createTask(String courseId, String description, LocalDate dueDate) {
		Task task = new Task(null, courseId, description, dueDate);
		try {
			task = taskRepo.save(task);
			return new Response(ResponseStatus.SUCCESS, "ID " + task.getTaskId());
		}catch(Exception e) {
			return new Response(ResponseStatus.FAIL, "Failed to create task");
		}
	}

	@Override
	public Response submitTask(String taskId, String studentId, String file) {
		TaskSubmission taskSubmission = new TaskSubmission(null, taskId, studentId, file, LocalDate.now(), -1);
		
		try {
			Task task = taskRepo.findById(taskId);
			if(!taskSubmission.submittedBeforeDueDate(task.getDueDate())) {
				return new Response(ResponseStatus.FAIL, "Missed due date");
			}
			
			taskSubmission = taskSubmissionRepo.save(taskSubmission);
			return new Response(ResponseStatus.SUCCESS, "ID " + taskSubmission.getSubmissionId());
		} catch(TaskNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Task doesn't exist");
		} catch(Exception e) {
			return new Response(ResponseStatus.FAIL, "Failed to persist");
		}
	}

	@Override
	public Response assignScore(String taskSubmissionId, int score) {		
		try {
			TaskSubmission taskSubmission = taskSubmissionRepo.findById(taskSubmissionId);
			taskSubmission.assignScore(score);
			taskSubmissionRepo.save(taskSubmission);
			return new Response(ResponseStatus.SUCCESS, "Updated score");
		} catch(InvalidScoreException e) {
			return new Response(ResponseStatus.FAIL, "Invalid score");
		} catch(TaskSubmissionNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Submission doesn't exists");
		} catch(Exception e) {
			return new Response(ResponseStatus.FAIL, "Failed to persist");
		}
	}
	
}
