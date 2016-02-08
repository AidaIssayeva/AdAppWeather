package com.videri.core.rule.receiver;

import java.util.Map;

public interface ConditionReceiver extends Receiver {

	boolean evalCondition(String object, String method, Map<String, Object> args);

}
