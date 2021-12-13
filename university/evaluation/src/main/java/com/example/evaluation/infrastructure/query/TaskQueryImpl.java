package com.example.evaluation.infrastructure.query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evaluation.application.query.TaskQuery;
import com.example.evaluation.application.query.read_model.TaskReadModel;
import com.example.evaluation.application.query.read_model.TaskSubmissionReadModel;
import com.example.evaluation.domain.model.Course;
import com.example.evaluation.infrastructure.data_model.TaskDataModel;
import com.example.evaluation.infrastructure.data_model.TaskSubmissionDataModel;
import com.example.evaluation.infrastructure.repository.CourseJpaRepository;
import com.example.evaluation.infrastructure.repository.TaskDataModelRepository;
import com.example.evaluation.infrastructure.repository.TaskSubmissionDataModelRepository;

@Component
public class TaskQueryImpl implements TaskQuery {
	Logger log = LoggerFactory.getLogger(TaskQueryImpl.class);
	
	@Autowired
	private TaskDataModelRepository taskRepo;

	@Autowired
	private TaskSubmissionDataModelRepository taskSubmissionRepo;

	@Autowired
	private CourseJpaRepository courseRepo;

	@Override
	public TaskSubmissionReadModel getTaskSubmission(String taskId, String studentId) {
		Optional<TaskSubmissionDataModel> taskDM = taskSubmissionRepo.findByTaskIdAndStudentId(taskId, studentId);
		if (taskDM.isPresent()) {
			return mapToTaskSubmissionReadModel(taskDM.get());
		}
		else {
			return new TaskSubmissionReadModel(studentId, taskId, null, null, -1);
		}
	}

	@Override
	public List<TaskSubmissionReadModel> getTaskSubmissions(String taskId) {
		return taskSubmissionRepo.findByTaskId(taskId).stream()
				.map(taskSubmissionDM -> mapToTaskSubmissionReadModel(taskSubmissionDM)).collect(Collectors.toList());
	}

	@Override
	public List<TaskReadModel> getTasks(String userId) {
		List<Course> courses = courseRepo.findByTeacherId(userId);
		if (courses.size() == 0)
			courses = courseRepo.findByStudentIds(userId);
		return taskRepo.findByCourseIdIn(courses.stream().map(course -> course.getCourseId()).collect(Collectors.toList()))
				.stream().map(task -> mapToTaskReadModel(task)).collect(Collectors.toList());
	}

	// Mappings to read model
	private TaskReadModel mapToTaskReadModel(TaskDataModel task) {
		return new TaskReadModel(task.getTaskId(), task.getCourseId(), task.getDescription(), task.getDueDate(),
				task.getWeight());
	}

	private TaskSubmissionReadModel mapToTaskSubmissionReadModel(TaskSubmissionDataModel taskSubmissionDM) {
		return new TaskSubmissionReadModel(taskSubmissionDM.getStudentId(), taskSubmissionDM.getTaskId(),
				taskSubmissionDM.getFile(), taskSubmissionDM.getDateSubmited(), taskSubmissionDM.getScore());
	}

}
