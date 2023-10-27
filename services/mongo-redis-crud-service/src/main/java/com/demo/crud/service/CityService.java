package com.demo.crud.service;

import org.springframework.data.domain.Page;

import com.demo.crud.model.City;

public interface CityService {
	boolean save(City city);
	
	City fetchOne(Long id);
	
	Page<City> fetchPage(int offset, int limit);
	
	Page<City> search(int offset, int limit, String sort, String searchStr);
	
	boolean update(Long id, City city);
	
	boolean delete(Long id);
}
