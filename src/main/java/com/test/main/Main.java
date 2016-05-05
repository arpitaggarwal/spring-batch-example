package com.test.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.service.JobService;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml");
		JobService jobService = (JobService) context
				.getBean("jobService");
		jobService.run();
		context.close();
	}

}
