package com.demo.migrations.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.demo.migrations.batch.config.MigrationJobExecStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobListener implements JobExecutionListener  {
	
	private MigrationJobExecStatus migrationJobExecStatus;
	
	public JobListener(MigrationJobExecStatus migrationJobExecStatus) {
		super();
		this.migrationJobExecStatus = migrationJobExecStatus;
		
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		migrationJobExecStatus.getParams().put("DuplicateKeyCounter", 0L);
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		long cnt = migrationJobExecStatus.getParams().get("DuplicateKeyCounter");
		log.info("AFTER JOB CNT IS : " + cnt);
		
		if(cnt > 0L) {
			jobExecution.setExitStatus(new ExitStatus("Duplicate keys found"));
		}
	}
}
