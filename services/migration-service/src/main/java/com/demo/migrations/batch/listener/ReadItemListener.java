package com.demo.migrations.batch.listener;

import org.springframework.batch.core.ItemReadListener;

import com.demo.migrations.model.mysql.SourceCity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadItemListener implements ItemReadListener<SourceCity> {

	@Override
	public void beforeRead() {
		// TODO Auto-generated method stub
		//ItemReadListener.super.beforeRead();
		log.debug("Before Reading");
	}

	@Override
	public void afterRead(SourceCity item) {
		// TODO Auto-generated method stub
		
		//ItemReadListener.super.afterRead(item);
		log.debug("after  Reading " + item.toString());
	}

	@Override
	public void onReadError(Exception ex) {
		// TODO Auto-generated method stub
		//ItemReadListener.super.onReadError(ex);
		log.error("Error in reading " + ex.getMessage());
	}

}
