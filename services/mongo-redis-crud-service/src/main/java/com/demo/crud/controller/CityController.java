package com.demo.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.crud.model.City;
import com.demo.crud.service.CityService;
import com.demo.crud.util.CustomResponseEntity;

@RestController
@RequestMapping("/api")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/city/{id}")
	@Cacheable (value = "city", key = "#p0")
	public CustomResponseEntity<City> fetchOne(@PathVariable("id") long id){
		City response = cityService.fetchOne(id);
		
		return new CustomResponseEntity<City>(response, HttpStatus.OK);
	}
	
	@GetMapping("/city")
	public CustomResponseEntity<List<City>> search(@RequestParam("offset") int offset, 
			@RequestParam("limit") int limit, @RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "searchStr", required = false) String searchStr){
		Page<City> cityPage = cityService.search(offset, limit, sort, searchStr);
		
		return new CustomResponseEntity<List<City>>(cityPage.getContent(), HttpStatus.OK);
	}
	
	@PostMapping("/city")
	@CachePut(value = "city", key = "#p0")
	public CustomResponseEntity<Boolean> save(@RequestBody City city){
		return new CustomResponseEntity<Boolean>(cityService.save(city), HttpStatus.CREATED);
	}
	
	@PutMapping("/city/{id}")
	@CachePut(value = "city", key = "#p0")
	public CustomResponseEntity<Boolean> update(@RequestBody City city, @PathVariable("id") long id){
		return new CustomResponseEntity<Boolean>(cityService.update(id, city), HttpStatus.OK);
	}
	
	@DeleteMapping("/city/{id}")
	@CacheEvict(value = "city", key = "#p0")
	public CustomResponseEntity<Boolean> delete(@PathVariable("id") long id){
		return new CustomResponseEntity<Boolean>(cityService.delete(id), HttpStatus.OK);
	}

}
