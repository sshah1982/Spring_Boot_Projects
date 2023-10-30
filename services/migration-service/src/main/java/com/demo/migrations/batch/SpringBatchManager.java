package com.demo.migrations.batch;

import java.time.LocalDateTime;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.migrations.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SpringBatchManager {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	
	@SuppressWarnings("unlikely-arg-type")
	public void invokeBatchJob() throws Exception  {
		try {
			log.info("Starting batch job, time: {}", LocalDateTime.now());
			JobParameters jobParam = new JobParametersBuilder()
					.addString("JobID", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();
			JobExecution execution = jobLauncher.run(job, jobParam);		
			
			log.info("Batch job completed, status: {},  exit status: {}, time: {}", execution.getStatus(), execution.getExitStatus(), LocalDateTime.now());
			
			if (execution.getExitStatus().toString().contains("Duplicate keys found")) {
				throw new Exception("Duplicate Keys Found");
			}
			if (execution.getStatus() != BatchStatus.COMPLETED) {
				String stackTrace = CommonUtils.getRootCauseStackTrace(execution.getAllFailureExceptions());
				throw new Exception(stackTrace);
			}
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			log.error("Error in batch Job " + e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		} catch (Exception be) {
			log.error("Error in batch Job " + be.getMessage());
			be.printStackTrace();
			throw new Exception(be.getMessage());
			
		}
	}
	
	

}
