package com.example.evaluation.application.query;

import java.util.List;

import com.example.evaluation.API.rest.view_model.CourseViewModel;
import com.example.evaluation.application.query.read_model.TaskReadModel;
import com.example.evaluation.application.query.read_model.TaskSubmissionReadModel;

public interface TaskQuery {

	TaskSubmissionReadModel getTaskSubmission(String taskId, String studentId);

	List<TaskSubmissionReadModel> getTaskSubmissions(String taskId, String teacherId);

	List<TaskReadModel> getTasks(String userId);

	List<CourseViewModel> getTaskResponsibilities(String teacherId);

}
