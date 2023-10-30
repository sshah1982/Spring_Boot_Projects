package com.demo.migrations.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.migrations.model.mysql.SourceCity;
import com.demo.migrations.repository.mongo.CityRepository;

public class MongoCustomWriter implements ItemWriter<SourceCity>{
	
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public void write(Chunk<? extends SourceCity> cities) throws Exception {
		cityRepository.saveAll(cities);
	}
}