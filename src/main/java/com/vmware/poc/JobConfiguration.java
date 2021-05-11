package com.vmware.poc;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.vmware.poc.classifier.RecordRangePartioner;
import com.vmware.poc.model.Employee;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	private String filepath;

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Bean("partitioner")
	@StepScope
	public RecordRangePartioner partitioner(String filepath) {
		RecordRangePartioner recordRangePartioner = new RecordRangePartioner();
		recordRangePartioner.setFilepath(filepath);
		return recordRangePartioner;
	}

	@Bean
	public FlatFileItemReader<Employee> itemReader(@Value("#{stepExecutionContext['minValue']}") Long minValue,
			@Value("#{stepExecutionContext['maxValue']}") Long maxValue) {
		return new FlatFileItemReaderBuilder<Employee>().name("employeeItemReader").lineMapper(lineMapper()).build();
	}

	@Bean
	public JdbcBatchItemWriter<Employee> itemWriter() {
		return new JdbcBatchItemWriterBuilder<Employee>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO people (name, age) VALUES (:name, :age)").dataSource(dataSource).build();
	}

	@Bean
	public LineMapper<Employee> lineMapper() {
		return (line, lineNumber) -> {
			line = line.trim();
			int len = line.length();
			return new Employee(line.substring(0, len - 3), Integer.valueOf(line.substring(len - 2)));
		};
	}

//Master
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").partitioner(slaveStep().getName(), partitioner(filepath))
				.step(slaveStep()).gridSize(8).taskExecutor(new SimpleAsyncTaskExecutor()).build();
	}

// slave step
	@Bean
	public Step slaveStep() {
		return stepBuilderFactory.get("slaveStep").<Employee, Employee>chunk(10000).reader(itemReader(null, null))
				.writer(itemWriter()).build();
	}

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").start(step1()).build();
	}
}
