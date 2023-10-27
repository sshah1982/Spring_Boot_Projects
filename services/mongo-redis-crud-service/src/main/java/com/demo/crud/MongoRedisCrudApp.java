package com.demo.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.demo.crud.repository.CityRepository;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class })
@EnableCaching
@EnableMongoRepositories(basePackageClasses = CityRepository.class)
public class MongoRedisCrudApp {
	public static void main(String[] args) {
		SpringApplication.run(MongoRedisCrudApp.class, args);
	}
	
	
}
