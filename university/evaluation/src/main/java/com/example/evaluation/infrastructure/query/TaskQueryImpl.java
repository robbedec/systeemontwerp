package com.example.evaluation.infrastructure.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evaluation.API.rest.view_model.CourseViewModel;
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
	public List<TaskSubmissionReadModel> getTaskSubmissions(String taskId, String teacherId) {
		//Check if task exists
		Optional<TaskDataModel> task = taskRepo.findById(taskId);
		if(task.isEmpty())
			return new ArrayList<>();
		
		//Check if teacher is responsible for the course
		Optional<Course> course = courseRepo.findById(task.get().getCourseId());
		if(course.isEmpty() || !course.get().getTeacherId().equals(teacherId))
			return new ArrayList<>();
		
		return taskSubmissionRepo.findByTaskId(taskId).stream()
				.map(taskSubmissionDM -> mapToTaskSubmissionReadModel(taskSubmissionDM)).collect(Collectors.toList());
	}

	@Override
	public List<TaskReadModel> getTasks(String userId) {
		//Check if user is of type teacher or student
		List<Course> courses = courseRepo.findByTeacherId(userId);
		if (courses.size() == 0)
			courses = courseRepo.findByStudentIds(userId);
		
		return taskRepo.findByCourseIdIn(courses.stream().map(course -> course.getCourseId()).collect(Collectors.toList()))
				.stream().map(task -> mapToTaskReadModel(task)).collect(Collectors.toList());
	}

	// Mappings to read model
	private TaskReadModel mapToTaskReadModel(TaskDataModel task) {
		String courseName = courseRepo.findById(task.getCourseId()).get().getCourseName();
		return new TaskReadModel(task.getTaskId(), task.getCourseId(), task.getDescription(), courseName, task.getDueDate(),
				task.getWeight());
	}

	private TaskSubmissionReadModel mapToTaskSubmissionReadModel(TaskSubmissionDataModel taskSubmissionDM) {
		return new TaskSubmissionReadModel(taskSubmissionDM.getStudentId(), taskSubmissionDM.getTaskId(),
				taskSubmissionDM.getFile(), taskSubmissionDM.getDateSubmited(), taskSubmissionDM.getScore());
	}

	@Override
	public List<CourseViewModel> getTaskResponsibilities(String teacherId) {
		List<CourseViewModel> responsibilities = new ArrayList<>();
		List<Course> courses = courseRepo.findByTeacherId(teacherId);
		for(Course course : courses) {
			responsibilities.add(new CourseViewModel(course.getCourseId(), course.getCourseName()));
		}
		return responsibilities;
	}

}
