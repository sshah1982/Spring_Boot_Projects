package com.demo.migrations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MysqlToMongdbMigration {

	public static void main(String[] args) {
		SpringApplication.run(MysqlToMongdbMigration.class, args);
	}
}
