package com.demo.migrations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.migrations.batch.SpringBatchManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MigrationController {
	
	@Autowired
	private SpringBatchManager manager;
	
	@GetMapping("/invoke")
	public ResponseEntity<String> invoke() throws Exception {
		try {
			log.info("Invoked batch job through controller");
			manager.invokeBatchJob();
			log.info("Invoked batch job through controller completed");
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch(Exception e) {
			//return ResponseHandler.generateResponse("Error happened while processing data!", HttpStatus.CONFLICT, e.getMessage(), request.getRequestURI());
			return new ResponseEntity<String>("Error happened while processing data!", HttpStatus.CONFLICT);
		}
	}
}