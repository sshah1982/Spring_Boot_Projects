package com.demo.migrations.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.demo.migrations.batch.listener.JobListener;
import com.demo.migrations.batch.listener.ReadItemListener;
import com.demo.migrations.batch.writer.MongoCustomWriter;
import com.demo.migrations.model.mysql.SourceCity;
import com.demo.migrations.util.CityRowMapper;
import com.demo.migrations.util.Constants;

@Configuration
public class SpringBatchConfig {
	
	@Autowired
	private DataSource dataSource;

	@Bean
	protected PlatformTransactionManager transactionManager(DataSource ds) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	@StepScope
	protected MongoCustomWriter mongoWriter() {
		return new MongoCustomWriter();
	}

	@Bean
	@StepScope
	protected JdbcCursorItemReader<SourceCity> itemReader() {
		return new JdbcCursorItemReaderBuilder<SourceCity>().dataSource(dataSource).name("sourceCityReader")
				.sql(Constants.MYSQL_READER_QUERY).rowMapper(new CityRowMapper()).build();

	}

	@Bean
	@Qualifier("step")
	protected Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("migrationStep", jobRepository).<SourceCity, SourceCity>chunk(10, transactionManager)
				.reader(itemReader()).writer(mongoWriter()).listener(new ReadItemListener()).listener(new JobListener()).build();
	}

	@Bean(name = "job")
	protected Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("migrationjob", jobRepository).start(step(jobRepository, transactionManager)).build();
	}

}
