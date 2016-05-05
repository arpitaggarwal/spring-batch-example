package com.test.processor;

import org.springframework.batch.item.ItemProcessor;

import com.test.model.Employee;

public class EmployeeProcessor implements ItemProcessor<Employee, String> {

	@Override
	public String process(Employee item) throws Exception {
		System.out.println(item);
		return item.getName();
	}

}
