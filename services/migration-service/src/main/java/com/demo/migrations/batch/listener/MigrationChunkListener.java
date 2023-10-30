package com.demo.migrations.batch.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;


public class MigrationChunkListener implements ChunkListener {
	
	private ChunkContext chunkContext;
	
	@Override
	public void beforeChunk(ChunkContext context) {
		this.chunkContext = context;
	}

	@Override
	public void afterChunk(ChunkContext context) {
		
	}

	@Override
	public void afterChunkError(ChunkContext context) {
//		log.error("IN CHUNK ERROR " );
//		
//		long cnt = context.getStepContext().getStepExecution().getJobExecution().getExecutionContext().getLong("DuplicateKeyCounter");
//		log.error("IN CHUNK ERROR Counter " + cnt );
//		
//		//context
//		context.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putLong("DuplicateKeyCounter", cnt++);
	}

	public ChunkContext getChunkContext() {
		return chunkContext;
	}

	public void setChunkContext(ChunkContext chunkContext) {
		this.chunkContext = chunkContext;
	}
	
	
}
