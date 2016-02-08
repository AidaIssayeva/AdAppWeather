package com.videri.core.state;

import com.videri.core.util.InternalLogger;

import java.util.Map;


public abstract class State {
	
	static final String LT = "lt";
	static final String LTE = "lte";
	static final String GT = "gt";
	static final String GTE = "gte";
	static final String MAX = "max";
	static final String MIN = "min";
	
	static final String ENTITIES = "entities";
	static final String BLOCK = "block";
	static final String BLOCKCONTEXT = "blockContext";
	
	static final String TIME = "time";
	
	protected String optStringArg(String object, String method, String arg, Map<String, Object> args) throws Exception {
		String ret = null;
		try {
			ret = (String) args.get(arg);
		} catch (Exception e) {
			InternalLogger.e("'" + object + "." + method + "' requires a '" + arg + "' arg of String type.", e);
			throw e;
		}
		return ret;
	}
	
	protected String getStringArg(String object, String method, String arg, Map<String, Object> args) throws Exception {
		String ret = optStringArg(object, method, arg, args);
		if(ret == null) {
			throw new IllegalArgumentException();
		}
		return ret;
	}
	
	protected Boolean optBooleanArg(String object, String method, String arg, Map<String, Object> args) throws Exception {
		Boolean ret = null;
		try {
			ret = (Boolean) args.get(arg);
		} catch (Exception e) {
			InternalLogger.e("'" + object + "." + method + "' requires a '" + arg + "' arg of Boolean type.", e);
			throw e;
		}
		return ret;
	}
	
	protected Boolean getBooleanArg(String object, String method, String arg, Map<String, Object> args) throws Exception {
		Boolean ret = optBooleanArg(object, method, arg, args);
		if(ret == null) {
			throw new IllegalArgumentException();
		}
		return ret;
	}
	
	protected Long optLongArg(String object, String method, String arg, Map<String, Object> args) throws Exception {
		Long ret = null;
		try {
			ret = Long.parseLong(args.get(arg).toString());
		} catch (Exception e) {
			InternalLogger.e("'" + object + "." + method + "' requires a '" + arg + "' arg of Long type.", e);			
			throw e;
		}
		return ret;
	}
	
	protected Long getLongArg(String object, String method, String arg, Map<String, Object> args) throws Exception {
		Long ret = optLongArg(object, method, arg, args);
		if(ret == null) {
			throw new IllegalArgumentException();
		}
		return ret;
	}
	
	protected Integer optIntArg(String object, String method, String arg, Map<String, Object> args) throws Exception {
		Integer ret = null;
		try {
			ret = (Integer) args.get(arg);
		} catch (Exception e) {
			InternalLogger.e("'" + object + "." + method + "' requires a '" + arg + "' arg of Integer type.", e);			
			throw e;
		}
		return ret;
	}
	
	protected Integer getIntArg(String object, String method, String arg, Map<String, Object> args) throws Exception {
		Integer ret = optIntArg(object, method, arg, args);
		if(ret == null) {
			throw new IllegalArgumentException();
		}
		return ret;
	}
}
