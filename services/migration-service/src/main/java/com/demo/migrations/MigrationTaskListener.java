package com.demo.migrations;

import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MigrationTaskListener implements TaskExecutionListener {

	@Override
	public void onTaskEnd(TaskExecution arg0) {
		log.info("Task completed!");
	}

	@Override
	public void onTaskFailed(TaskExecution arg0, Throwable arg1) {
		log.info("Task failed!");

	}

	@Override
	public void onTaskStartup(TaskExecution arg0) {
		log.info("Task Started!");
	}
}