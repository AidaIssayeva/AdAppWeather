package com.videri.core.rule;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Trigger {

//	protected Rule rule;
//	protected String key;
//	protected String object;
//	protected String method;
//	protected Map<String, Object> args;
//	protected Listener listener;
//
//	Trigger(Rule rule) {
//		this.rule = rule;
//	}
//
//	void init(JSONObject triggerJson) throws Exception {
//		InternalLogger.d("Initializing trigger for rule with key '" + rule.getKey() + "'.");
//		this.key = parseKey(triggerJson);
//		this.object = parseObject(triggerJson);
//		this.method = parseMethod(triggerJson);
//		this.args = parseArgs(triggerJson);
//
//		validateObjectAndMethod();
//	}
//
//	private String parseKey(JSONObject triggerJson) throws Exception {
//		String key = rule.getKey();
//		try {
//			key += "_" + triggerJson.getString("key");
//		} catch (JSONException e) {
//			InternalLogger.i("Error parsing 'key' for trigger in rule with key " + rule.getKey() + ".");
//		}
//		return key;
//	}
//
//	private String parseObject(JSONObject triggerJson) throws Exception {
//		String object;
//		try {
//			object = triggerJson.getString("object");
//		} catch (JSONException e) {
//			InternalLogger.w("Error parsing 'object' for trigger in rule with key " + rule.getKey() + ".", e);
//			throw e;
//		}
//		return object;
//	}
//
//	private String parseMethod(JSONObject triggerJson) throws Exception {
//		String method;
//		try {
//			method = triggerJson.getString("method");
//		} catch (JSONException e) {
//			InternalLogger.w("Error parsing 'method' for trigger in rule with key " + rule.getKey() + ".", e);
//			throw e;
//		}
//
//		return method;
//	}
//
//	private Map<String, Object> parseArgs(JSONObject triggerJson) throws JSONException {
//		JSONObject argsJson = triggerJson.optJSONObject("args");
//		Map<String, Object> args = new HashMap<String,Object>();
//		if(argsJson != null) {
//			Iterator<String> it = argsJson.keys();
//			while(it.hasNext()) {
//				String key = it.next();
//				try {
//					if(argsJson.isNull(key))
//						args.put(key, null);
//					else
//						args.put(key,argsJson.get(key));
//				} catch (JSONException e) {
//					//This will never happen
//					InternalLogger.wtf("Error parsing argument with key " + key  + " from trigger in rule with key " + rule.getKey() + ".", e);
//					throw e;
//				}
//			}
//		}
//		return args;
//	}
//
//	void initListener() {
//		TriggerReceiver receiver = AK.getRuleManager().getTriggerReceiver(object, method);
//		if(receiver != null) {
//			listener = receiver.initTrigger(new Runnable() {
//
//				@Override
//				public void run() {
//					handle();
//				}
//			}, key, object, method, args);
//		}
//	}
//
//	void shutdown() {
//		InternalLogger.d("Shutting down trigger '" + object + "." + method + ".");
//		TriggerReceiver receiver = AK.getRuleManager().getTriggerReceiver(object, method);
//		if(receiver != null) {
//			receiver.shutdownTrigger(listener, object, method, args);
//		}
//	}
//
//	public void handle() {
//		InternalLogger.d("'" + object + "." + method + " trigger is firing.");
//		rule.handle();
//	}
//
//	private void validateObjectAndMethod() {
//		if(AK.getRuleManager().getTriggerReceiver(object, method) == null) {
//			throw new IllegalArgumentException("TriggerReceiver has not been registered with object " + object + " and method " + method);
//		}
//	}
}
