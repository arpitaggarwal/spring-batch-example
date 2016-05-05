package com.test.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class EmployeeReader implements ItemReader<String> {

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}