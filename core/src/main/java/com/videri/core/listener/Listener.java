package com.videri.core.listener;

public interface Listener<T extends Event> {
	void handle(T event);
}
