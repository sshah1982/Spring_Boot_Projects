package com.demo.migrations.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="city")
public class City {
	
	@Id
	private long id;
	
	@Field
	private String name;
	
	@Field
	private String countryCode;
	
	@Field
	private String district;
	
	@Field
	private long population; 
}
