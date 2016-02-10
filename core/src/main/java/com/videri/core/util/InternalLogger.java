package com.videri.core.util;

import android.util.Log;

public class InternalLogger {
	
	private static final String TAG = "Videri";
	
	private static boolean enabled = false;
	
	public static void setLoggingEnabled(boolean enabled) {
		InternalLogger.enabled = enabled;
	}
	
	public static boolean isLoggingEnabled() {
		return enabled;
	}
	
	public static void v(String message){
		if(enabled) {
			Log.v(TAG, message);
		}
	}
	
	public static void d(String message){
		if(enabled) {
			Log.d(TAG, message);
		}
	}
	
	public static void i(String message){
		if(enabled) {
			Log.i(TAG, message);
		}
	}
	
	public static void w(String message){
		if(enabled) {
			Log.w(TAG, message);
		}
	}
	
	public static void w(String message, Throwable t){
		if(enabled) {
			Log.w(TAG, message, t);
		}
	}
	
	public static void e(String message, Throwable t){
		if(enabled) {
			Log.e(TAG, message, t);
		}
	}

	public static void wtf(String message, Throwable t){
		if(enabled) {
			Log.wtf(TAG, message, t);
		}
	}
}
