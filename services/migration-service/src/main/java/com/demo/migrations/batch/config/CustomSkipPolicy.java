package com.demo.migrations.batch.config;

import org.springframework.batch.core.listener.StepListenerFailedException;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.dao.DuplicateKeyException;

import com.mongodb.MongoBulkWriteException;

public class CustomSkipPolicy implements SkipPolicy {

	private static final long MAX_SKIP_COUNT = Long.MAX_VALUE;

	@Override
	public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException {
		// TODO Auto-generated method stub
		if (t instanceof DuplicateKeyException && skipCount < MAX_SKIP_COUNT) {
			return true;
		}
		
		if (t instanceof MongoBulkWriteException && skipCount < MAX_SKIP_COUNT) {
			return true;
		}
		
		if (t instanceof StepListenerFailedException && skipCount < MAX_SKIP_COUNT) {
			return true;
		}

		return false;
	}
}