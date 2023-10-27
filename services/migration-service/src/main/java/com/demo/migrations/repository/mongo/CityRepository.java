package com.demo.migrations.repository.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.demo.migrations.model.mongo.City;
import com.demo.migrations.model.mysql.SourceCity;

@Repository
public class CityRepository{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void saveAll(Chunk<? extends SourceCity> cities) {
		List<? extends SourceCity> lst = cities.getItems();
		List<City> newLst = new ArrayList<City>();
		
		for(SourceCity sc : lst) {
			City city = new City();
			BeanUtils.copyProperties(sc, city);
			newLst.add(city);
		}
		
		
		mongoTemplate.insert(lst, City.class);
	}
	

}