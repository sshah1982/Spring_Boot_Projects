package com.demo.migrations.batch.listener;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import com.demo.migrations.model.mysql.SourceCity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReadItemListener implements ItemReadListener<SourceCity> {

	@Override
	public void beforeRead() {
		log.debug("Before Reading");
	}

	@Override
	public void afterRead(SourceCity item) {
		log.debug("after  Reading " + item.toString());
	}

	@Override
	public void onReadError(Exception ex) {
		log.error("Error in reading " + ex.getMessage());
	}

}
