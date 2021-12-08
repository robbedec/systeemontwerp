package com.example.evaluation.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import com.example.evaluation.domain.model.TaskSubmission;
import com.example.evaluation.domain.repository.TaskSubmissionRepository;
import com.example.evaluation.infrastructure.data_model.TaskSubmissionDataModel;
import com.example.evaluation.infrastructure.exception.TaskSubmissionNotFoundException;

@Repository
public class TaskSubmissionRepositoryImpl implements TaskSubmissionRepository {
	
	@Autowired
	private TaskSubmissionDataModelRepository taskSubmissionDMRepo;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	private TaskSubmission mapToTaskSubmission(TaskSubmissionDataModel taskSubmissionDM) {
		return new TaskSubmission(taskSubmissionDM.getSubmissionId(), taskSubmissionDM.getTaskId(), taskSubmissionDM.getStudentId(), 
				taskSubmissionDM.getFile(), taskSubmissionDM.getDateSubmited(), taskSubmissionDM.getScore());
	}
	
	private TaskSubmissionDataModel mapToTaskSubmissionDataModel(TaskSubmission taskSubmission) {
		return new TaskSubmissionDataModel(taskSubmission.getSubmissionId(), taskSubmission.getTaskId(), taskSubmission.getStudentId(), 
				taskSubmission.getFile(), taskSubmission.getDateSubmitted(), taskSubmission.getScore());
	}

	@Override
	public TaskSubmission findById(String taskSubmissionId) {
		TaskSubmissionDataModel taskSubmissionDM = taskSubmissionDMRepo.findById(taskSubmissionId).orElseThrow(TaskSubmissionNotFoundException::new);
		return mapToTaskSubmission(taskSubmissionDM);
	}

	@Override
	public TaskSubmission save(TaskSubmission taskSubmission) {
		TaskSubmissionDataModel taskDM = taskSubmissionDMRepo.save(mapToTaskSubmissionDataModel(taskSubmission));
		
		taskSubmission.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
		
		return mapToTaskSubmission(taskDM);
	}

	@Override
	public List<TaskSubmission> findByTaskId(String taskId) {
		return taskSubmissionDMRepo.findByTaskId(taskId).stream()
				.map(taskSubmissionDM -> mapToTaskSubmission(taskSubmissionDM)).collect(Collectors.toList());
	}

	@Override
	public TaskSubmission findByTaskIdAndStudentId(String taskId, String studentId) {
		TaskSubmissionDataModel taskSubmissionDM = taskSubmissionDMRepo.findByTaskIdAndStudentId(taskId, studentId).orElseThrow(TaskSubmissionNotFoundException::new);
		return mapToTaskSubmission(taskSubmissionDM);
	}
	
}
