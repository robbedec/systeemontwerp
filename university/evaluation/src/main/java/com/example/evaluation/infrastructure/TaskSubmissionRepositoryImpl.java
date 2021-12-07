package com.example.evaluation.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.evaluation.domain.TaskSubmission;
import com.example.evaluation.domain.TaskSubmissionRepository;

@Repository
public class TaskSubmissionRepositoryImpl implements TaskSubmissionRepository {
	
	@Autowired
	TaskSubmissionDataModelRepository taskSubmissionDMRepo;
	
	private TaskSubmission mapToTaskSubmission(TaskSubmissionDataModel taskSubmissionDM) {
		return new TaskSubmission(taskSubmissionDM.getSubmissionId(), taskSubmissionDM.getTaskId(), taskSubmissionDM.getStudentId(), 
				taskSubmissionDM.getFile(), taskSubmissionDM.getDateSubmited(), taskSubmissionDM.getScore());
	}
	
	private TaskSubmissionDataModel mapToTaskSubmissionDataModel(TaskSubmission taskSubmission) {
		return new TaskSubmissionDataModel(taskSubmission.getSubmissionId(), taskSubmission.getTaskId(), taskSubmission.getStudentId(), 
				taskSubmission.getFile(), taskSubmission.getDateSubmited(), taskSubmission.getScore());
	}

	@Override
	public TaskSubmission findById(String taskSubmissionId) {
		TaskSubmissionDataModel taskSubmissionDM = taskSubmissionDMRepo.findById(taskSubmissionId).orElseThrow(TaskSubmissionNotFoundException::new);
		return mapToTaskSubmission(taskSubmissionDM);
	}

	@Override
	public void save(TaskSubmission taskSubmission) {
		taskSubmissionDMRepo.save(mapToTaskSubmissionDataModel(taskSubmission));
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