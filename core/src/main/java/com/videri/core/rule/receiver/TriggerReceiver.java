package com.videri.core.rule.receiver;


import com.videri.core.listener.Listener;

import java.util.Map;

public interface TriggerReceiver extends Receiver {

	Listener initTrigger(Runnable callback, String key, String object, String method, Map<String, Object> args);

	void shutdownTrigger(Listener listener, String object, String method, Map<String, Object> args);

}
