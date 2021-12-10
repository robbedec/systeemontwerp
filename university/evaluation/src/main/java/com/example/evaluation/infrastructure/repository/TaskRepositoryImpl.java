package com.example.evaluation.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.evaluation.domain.model.Task;
import com.example.evaluation.domain.repository.TaskRepository;
import com.example.evaluation.infrastructure.data_model.TaskDataModel;
import com.example.evaluation.infrastructure.exception.TaskNotFoundException;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
	@Autowired
	private TaskDataModelRepository taskDMRepo;

	private Task mapToTask(TaskDataModel taskDM) {
		return new Task(taskDM.getTaskId(), taskDM.getCourseId(),taskDM.getDescription() , taskDM.getDueDate(), taskDM.getWeight());
	}
	
	private TaskDataModel mapToTaskDataModel(Task task) {
		return new TaskDataModel(task.getTaskId(), task.getCourseId(), task.getDescription(), task.getDueDate(), task.getWeight());
	}
	
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
		return taskDMRepo.findByCourseId(courseId).stream()
				.map(taskDM -> mapToTask(taskDM)).collect(Collectors.toList());
	}

}
