package com.demo.migrations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableConfigurationProperties
@Configuration(proxyBeanMethods = false)
public class DataSourceConfiguration {

	@Autowired
	private Environment env;
	
	@Bean(name = "dataSource")
	@Primary
	protected DataSource constructDataSource() {
		return DataSourceBuilder.create().url(env.getProperty("spring.datasource.url"))
				.driverClassName(env.getProperty("spring.datasource.driver-class-name"))
				.username(env.getProperty("spring.datasource.username"))
				.password(env.getProperty("spring.datasource.password")).build();
	}
}