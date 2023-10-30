package com.demo.migrations.batch.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MigrationJobExecStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Long> params = new HashMap<String, Long>();

	public Map<String, Long> getParams() {
		return params;
	}

	public void setParams(Map<String, Long> params) {
		this.params = params;
	}
	
	

}
