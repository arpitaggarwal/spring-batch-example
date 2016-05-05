package com.test.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class EmployeeWriter implements ItemWriter<String> {
	
	@Override
	public void write(List<? extends String> obj)
			throws Exception {
		System.out.println(obj);
	}

}
