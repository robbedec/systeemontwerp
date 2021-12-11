package com.example.evaluation.application.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaluation.domain.exception.InvalidScoreException;
import com.example.evaluation.domain.model.Task;
import com.example.evaluation.domain.model.TaskSubmission;
import com.example.evaluation.domain.repository.CourseRepository;
import com.example.evaluation.domain.repository.TaskRepository;
import com.example.evaluation.infrastructure.exception.TaskNotFoundException;
import com.example.evaluation.infrastructure.exception.TaskSubmissionNotFoundException;

@Transactional
@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public Response createTask(String courseId, String description, LocalDateTime dueDate, double weight, String teacherId) {
		try {
			if(!teacherId.equals(courseRepo.findTeacherForCourse(courseId))) {
				return new Response(ResponseStatus.FAIL, "Only the teacher can create a task");
			}
			
			Task task = new Task(null, courseId, description, dueDate, weight);
			task = taskRepo.save(task);
			
			for(String studentId : courseRepo.findStudentsFollowingCourse(courseId)) {
				TaskSubmission taskSubmission = new TaskSubmission(courseId, studentId);
				taskRepo.save(taskSubmission);
			}
			
			return new Response(ResponseStatus.SUCCESS, "ID " + task.getTaskId());
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Failed to create task");
		}
	}

	@Override
	public Response submitTask(String taskId, String studentId, String file) {
		try {
			Task task = taskRepo.findById(taskId);
			if (task.dueDatePassed()) {
				return new Response(ResponseStatus.FAIL, "Missed due date");
			}
			TaskSubmission taskSubmission = taskRepo.findSubmissionByTaskIdAndStudentId(taskId, studentId);

			taskSubmission = taskRepo.save(taskSubmission);
			return new Response(ResponseStatus.SUCCESS, "ID " + taskSubmission.getSubmissionId());
		} catch (TaskNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Task doesn't exist");
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Failed to persist");
		}
	}

	@Override
	public Response assignScore(String taskSubmissionId, int score, String teacherId) {
		try {
			TaskSubmission taskSubmission = taskRepo.findSubmissionById(taskSubmissionId);
			
			String responsibleTeacher = courseRepo.findTeacherForCourse(taskRepo.findById(taskSubmission.getTaskId()).getCourseId());
			if(!teacherId.equals(responsibleTeacher)) {
				return new Response(ResponseStatus.FAIL, "Only the teacher can assign a score");
			}
			
			taskSubmission.assignScore(score);
			taskRepo.save(taskSubmission);
			return new Response(ResponseStatus.SUCCESS, "Updated score");
		} catch (InvalidScoreException e) {
			return new Response(ResponseStatus.FAIL, "Invalid score");
		} catch (TaskSubmissionNotFoundException e) {
			return new Response(ResponseStatus.FAIL, "Submission doesn't exists");
		} catch (Exception e) {
			return new Response(ResponseStatus.FAIL, "Failed to persist");
		}
	}

	@Override
	public Response checkPlagiarism(String taskId) {
		List<TaskSubmission> taskSubmissions = taskRepo.findSubmissionsByTaskId(taskId);
		Set<String> plagiarismTasks = new HashSet<>();

		for (int i = 0; i < taskSubmissions.size(); i++) {
			String file1 = taskSubmissions.get(i).getFile();
			if (file1 == null || file1.length() == 0)
				continue;

			for (int j = 0; j < taskSubmissions.size(); j++) {
				String file2 = taskSubmissions.get(j).getFile();
				if (file2 == null || file2.length() == 0)
					continue;

				double similarity = longestCommonSubsequence(file1, file2) / Math.max(file1.length(), file2.length());
				if (similarity > 0.5) {
					if (!plagiarismTasks.contains(taskSubmissions.get(i).getSubmissionId())) {
						taskSubmissions.get(i).plagiarismDetected();
						plagiarismTasks.add(taskSubmissions.get(i).getSubmissionId());
						taskRepo.save(taskSubmissions.get(i));
					}
					if (!plagiarismTasks.contains(taskSubmissions.get(j).getSubmissionId())) {
						taskSubmissions.get(j).plagiarismDetected();
						plagiarismTasks.add(taskSubmissions.get(j).getSubmissionId());
						taskRepo.save(taskSubmissions.get(j));
					}
				}
			}
		}
		return new Response(ResponseStatus.SUCCESS, "Submissions checked for plagiarism");
	}

	private int longestCommonSubsequence(String str1, String str2) {
		int length[][] = new int[str1.length() + 1][str2.length() + 1];
		for (int i = 0; i < str1.length(); i++) {
			length[i][0] = 0;
		}
		for (int i = 0; i < str2.length(); i++) {
			length[0][i] = 0;
		}

		for (int i = 1; i < str1.length() + 1; i++) {
			for (int j = 1; j < str2.length() + 1; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					length[i][j] = length[i - 1][j - 1] + 1;
				} else {
					length[i][j] = Math.max(length[i][j - 1], length[i - 1][j]);
				}
			}
		}
		return length[str1.length()][str2.length()];
	}

}
