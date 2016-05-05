package com.test.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class JobService {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier(value = "job")
	private Job job;

	public void run() {
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
		}
	}
}
