package com.example.evaluation.infrastructure.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evaluation.application.query.TaskQuery;
import com.example.evaluation.application.query.TaskSubmissionReadModel;
import com.example.evaluation.infrastructure.data_model.TaskSubmissionDataModel;
import com.example.evaluation.infrastructure.repository.TaskSubmissionDataModelRepository;

@Component
public class TaskQueryImpl implements TaskQuery {
	@Autowired
	private TaskSubmissionDataModelRepository taskSubmissionDMRepo;

	private TaskSubmissionReadModel mapToTaskSubmissionReadModel(TaskSubmissionDataModel taskSubmissionDM) {
		return new TaskSubmissionReadModel(taskSubmissionDM.getStudentId(), taskSubmissionDM.getTaskId(),
				taskSubmissionDM.getFile(), taskSubmissionDM.getDateSubmited(), taskSubmissionDM.getScore());
	}

	@Override
	public List<TaskSubmissionReadModel> getTaskSubmissions(String taskId) {
		return taskSubmissionDMRepo.findByTaskId(taskId).stream()
				.map(taskSubmissionDM -> mapToTaskSubmissionReadModel(taskSubmissionDM)).collect(Collectors.toList());
	}

}
