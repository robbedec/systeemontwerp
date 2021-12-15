package com.example.evaluation.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import com.example.evaluation.domain.model.Task;
import com.example.evaluation.domain.model.TaskSubmission;
import com.example.evaluation.domain.repository.TaskRepository;
import com.example.evaluation.infrastructure.data_model.TaskDataModel;
import com.example.evaluation.infrastructure.data_model.TaskSubmissionDataModel;
import com.example.evaluation.infrastructure.exception.TaskNotFoundException;
import com.example.evaluation.infrastructure.exception.TaskSubmissionNotFoundException;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

	@Autowired
	private TaskDataModelRepository taskDMRepo;

	@Autowired
	private TaskSubmissionDataModelRepository taskSubmissionDMRepo;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Override
	public Task findById(String taskId) {
		TaskDataModel taskDM = taskDMRepo.findById(taskId).orElseThrow(TaskNotFoundException::new);
		return mapToTask(taskDM);
	}

	@Override
	public Task save(Task task) {
		TaskDataModel taskDM = taskDMRepo.save(mapToTaskDataModel(task));
		return mapToTask(taskDM);
	}

	@Override
	public List<Task> findByCourseId(String courseId) {
		return taskDMRepo.findByCourseId(courseId).stream().map(taskDM -> mapToTask(taskDM))
				.collect(Collectors.toList());
	}

	@Override
	public TaskSubmission findSubmissionById(String taskSubmissionId) {
		TaskSubmissionDataModel taskSubmissionDM = taskSubmissionDMRepo.findById(taskSubmissionId)
				.orElseThrow(TaskSubmissionNotFoundException::new);
		return mapToTaskSubmission(taskSubmissionDM);
	}

	@Override
	public TaskSubmission save(TaskSubmission taskSubmission) {
		TaskSubmissionDataModel taskDM = taskSubmissionDMRepo.save(mapToTaskSubmissionDataModel(taskSubmission));

		taskSubmission.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
		taskSubmission.clearDomainEvents();

		return mapToTaskSubmission(taskDM);
	}

	@Override
	public List<TaskSubmission> findSubmissionsByTaskId(String taskId) {
		return taskSubmissionDMRepo.findByTaskId(taskId).stream()
				.map(taskSubmissionDM -> mapToTaskSubmission(taskSubmissionDM)).collect(Collectors.toList());
	}

	@Override
	public TaskSubmission findSubmissionByTaskIdAndStudentId(String taskId, String studentId) {
		TaskSubmissionDataModel taskSubmissionDM = taskSubmissionDMRepo.findByTaskIdAndStudentId(taskId, studentId)
				.orElseThrow(TaskSubmissionNotFoundException::new);
		return mapToTaskSubmission(taskSubmissionDM);
	}

	@Override
	public double findTotalWeight(String courseId) {
		Optional<Double> weight = taskDMRepo.findTotalWeight(courseId);
		if (weight.isEmpty())
			return 0;
		return weight.get();
	}

	// Mappings from domain model <-> data model
	private Task mapToTask(TaskDataModel taskDM) {
		return new Task(taskDM.getTaskId(), taskDM.getCourseId(), taskDM.getDescription(), taskDM.getDueDate(),
				taskDM.getWeight());
	}

	private TaskDataModel mapToTaskDataModel(Task task) {
		return new TaskDataModel(task.getTaskId(), task.getCourseId(), task.getDescription(), task.getDueDate(),
				task.getWeight());
	}

	private TaskSubmission mapToTaskSubmission(TaskSubmissionDataModel taskSubmissionDM) {
		return new TaskSubmission(taskSubmissionDM.getSubmissionId(), taskSubmissionDM.getTaskId(),
				taskSubmissionDM.getStudentId(), taskSubmissionDM.getFile(), taskSubmissionDM.getDateSubmited(),
				taskSubmissionDM.getScore());
	}

	private TaskSubmissionDataModel mapToTaskSubmissionDataModel(TaskSubmission taskSubmission) {
		return new TaskSubmissionDataModel(taskSubmission.getSubmissionId(), taskSubmission.getTaskId(),
				taskSubmission.getStudentId(), taskSubmission.getFile(), taskSubmission.getDateSubmitted(),
				taskSubmission.getScore());
	}

}
