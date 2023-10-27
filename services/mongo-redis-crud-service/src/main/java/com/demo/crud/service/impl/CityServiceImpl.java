package com.demo.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.crud.model.City;
import com.demo.crud.repository.CityRepository;
import com.demo.crud.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityRepository cityRepo;
	

	@Override
	public boolean save(City city) {
		// TODO Auto-generated method stub
		cityRepo.save(city);
		return true;
	}

	@Override
	public City fetchOne(Long id) {
		// TODO Auto-generated method stub
		return cityRepo.findById(id).get();
	}

	@Override
	public Page<City> fetchPage(int offset, int limit) {
		// TODO Auto-generated method stub
		Pageable page = PageRequest.of(offset, limit);
		return cityRepo.findAll(page);
	}

	@Override
	public boolean update(Long id, City city) {
		// TODO Auto-generated method stub
		if (cityRepo.existsById(id)) {
			cityRepo.save(city);
		}
		else {
			
		}
		
		return true;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		if (cityRepo.existsById(id)) {
			cityRepo.deleteById(id);
		}
		else {
			
		}
		
		return true;
	}

	@Override
	public Page<City> search(int offset, int limit, String sort, String searchStr) {
		// TODO Auto-generated method stub
		Pageable page = PageRequest.of(offset, limit);
		return cityRepo.findAll(page);
	}

	
}
