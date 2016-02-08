package com.videri.core.rule.receiver;

import java.util.List;
import java.util.Map;

public interface Receiver {

	abstract Map<String, List<String>> getMethodsToRegister();
}
