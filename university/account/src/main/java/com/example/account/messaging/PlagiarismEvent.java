package com.example.account.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlagiarismEvent {
	private String taskId;
	private String studentId;
}