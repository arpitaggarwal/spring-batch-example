package com.test.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import com.test.model.Employee;
import com.test.processor.EmployeeProcessor;
import com.test.writer.EmployeeWriter;

@Configuration
@EnableBatchProcessing
public class ApplicationConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	protected Job job() throws Exception {
		return jobBuilderFactory
				.get("job")
				.start(step1(reader(new ClassPathResource("employee.csv")),
						processor(), writer())).build();
	}

	@Bean
	protected Step step1(final ItemReader<Employee> reader,
			final ItemProcessor<Employee, String> processor,
			final ItemWriter<String> writer) {
		return stepBuilderFactory.get("step1").<Employee, String> chunk(5)
				.reader(reader).processor(processor).writer(writer).build();
	}

	@Bean
	protected FlatFileItemReader<Employee> reader(Resource fileName)
			throws Exception {
		DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		defaultLineMapper.setFieldSetMapper(new FieldSetMapper<Employee>() {
			@Override
			public Employee mapFieldSet(FieldSet fieldSet) throws BindException {
				return new Employee(fieldSet.readInt(0), fieldSet.readString(1));
			}
		});
		defaultLineMapper.afterPropertiesSet();
		FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
		reader.setLineMapper(defaultLineMapper);
		reader.setResource(fileName);
		reader.afterPropertiesSet();
		return reader;
	}

	@Bean
	protected ItemWriter<String> writer() {
		return new EmployeeWriter();
	}

	@Bean
	protected ItemProcessor<Employee, String> processor() {
		return new EmployeeProcessor();
	}

}
