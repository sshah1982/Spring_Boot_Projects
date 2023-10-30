package com.demo.migrations.batch.listener;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.listener.StepListenerFailedException;
import org.springframework.batch.item.Chunk;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.demo.migrations.batch.config.MigrationJobExecStatus;
import com.demo.migrations.model.mysql.SourceCity;
import com.mongodb.MongoBulkWriteException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WriteItemListener implements ItemWriteListener<SourceCity> {
	
	private MigrationJobExecStatus migrationJobExecStatus;
	
	public WriteItemListener(MigrationJobExecStatus migrationJobExecStatus) {
		super();
		this.migrationJobExecStatus = migrationJobExecStatus;
	}

	@Override
	public void beforeWrite(Chunk<? extends SourceCity> items) {
		// TODO Auto-generated method stub
		log.debug("Before Writing");
	}

	@Override
	public void afterWrite(Chunk<? extends SourceCity> items) {
		// TODO Auto-generated method stub
		log.debug("After Writing");
	}

	@Override
	public void onWriteError(Exception ex, Chunk<? extends SourceCity> items) {
		if (ex instanceof DuplicateKeyException || ex instanceof MongoBulkWriteException || ex instanceof StepListenerFailedException ) {
			for(SourceCity sc: items) {
				//log.info("This key already exists : " + sc.getId());
				
			}
			Long cnt = migrationJobExecStatus.getParams().get("DuplicateKeyCounter");
			cnt += items.size();
			migrationJobExecStatus.getParams().put("DuplicateKeyCounter", cnt);
		}
	}

	
}
