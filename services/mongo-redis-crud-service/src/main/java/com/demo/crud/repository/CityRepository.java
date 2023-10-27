package com.demo.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.crud.model.City;

@Repository
public interface CityRepository extends MongoRepository<City, Long> {
	
}
