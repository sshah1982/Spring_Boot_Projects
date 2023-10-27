package com.demo.migrations.model.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="city")
public class SourceCity {
	
	@Id
	private long id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="CountryCode")
	private String countryCode;
	
	@Column(name="District")
	private String district;
	
	@Column(name="Population")
	private long population;
}
