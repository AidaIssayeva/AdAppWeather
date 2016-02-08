package com.videri.core.state;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.videri.core.listener.Listener;
import com.videri.core.listener.NetworkStateChangeEvent;
import com.videri.core.listener.NetworkStateChangeListener;
import com.videri.core.rule.receiver.ConditionReceiver;
import com.videri.core.rule.receiver.TriggerReceiver;
import com.videri.core.util.InternalLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkState extends State implements TriggerReceiver, ConditionReceiver {
	
	private static final String STATE = "state";
	private static final String ON_STATE_CHANGE = "onStateChange";
	private static final String NETWORK = "network";

	protected static NetworkState instance;
	protected NetworkBroadcastReceiver receiver;

	protected List<NetworkStateChangeListener> stateChangeListeners;
	protected Type lastType = null;
	protected Type overriddenType = null;

	private Context mContext;

	public NetworkState(Context context){
		mContext = context;
	}

//	public static NetworkState instance(){
//		if(instance == null)
//			instance = new NetworkState();
//		return instance;
//	}
	
	public Type getCurrentType(){
		if(overriddenType != null) {
			return overriddenType;
		}
//		if(!AK.getConnectivityManager().isConnected()) {
//			return Type.NONE;
//		}
		
		ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		 
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		
		if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
			if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
				return Type.WIFI;
			} else {
				return Type.CELL;
			}
			
		} else {
			return Type.NONE;
		}
	}
	
	public Type getLastType(){
		return lastType;
	}

	public void addListener(Listener listener) {
		if(listener instanceof NetworkStateChangeListener) {
			if(stateChangeListeners == null || stateChangeListeners.size() == 0) {
				stateChangeListeners = new ArrayList<NetworkStateChangeListener>();
				startCheckingNetworkStateOnInterval();
			}
			stateChangeListeners.add((NetworkStateChangeListener) listener);
		}
	}

	public void removeListener(Listener listener) {
		if(listener instanceof NetworkStateChangeListener) {
			stateChangeListeners.remove((NetworkStateChangeListener) listener);
			if(stateChangeListeners.size() == 0) {
				stopCheckingNetworkStateOnInterval();
			}
		}
	}
	
	protected void notifyStateChangeListeners(){
		if(stateChangeListeners != null) {
			for(NetworkStateChangeListener listener : stateChangeListeners) {
				listener.handle(new NetworkStateChangeEvent(getLastType()));
			}
		}
	}
	
	protected void startCheckingNetworkStateOnInterval() {
		IntentFilter filter = new IntentFilter(NETWORK);
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		
		receiver = new NetworkBroadcastReceiver(this);
		mContext.registerReceiver(receiver, filter);
	}
	
	protected void stopCheckingNetworkStateOnInterval() {
		mContext.unregisterReceiver(receiver);
	}
	
	public void checkNetworkState() {
		InternalLogger.d("Checking network state for changes");
		Type currentType = getCurrentType();
		if(!currentType.equals(lastType)) {
			InternalLogger.d("Network state changed to " + currentType.toString());
			lastType = currentType;
			notifyStateChangeListeners();
		}
	}
	
	/**
	 * THIS IS FOR TESTING PURPOSES. DO NOT USE.
	 * Sets the current device network state to be used by the NetworkState.
	 * @param type the current network state
	 */
	public void mockNetworkState(Type type) {
		overriddenType = type;
		checkNetworkState();
	}

	public enum Type {
		NONE(0),
		CELL(1),
		WIFI(2);
		
		private int order;

		private Type(int order) {
			this.order = order;
		}
		
		public int compareTo(String other) {
			Type o = Type.valueOf(other.toUpperCase());
			return this.order - o.order;
		}
	}
	
	class NetworkBroadcastReceiver extends BroadcastReceiver {
		
		private NetworkState state;
		
		public NetworkBroadcastReceiver(NetworkState state) {
			this.state = state;
		}
		
		@Override
		public void onReceive(Context context, Intent intent) {
			checkNetworkState();
		}
	}

	@Override
	public boolean evalCondition(String object, String method, Map<String, Object> args) {
		boolean ret = true;
		
		String lt = "";
		try {
			lt = optStringArg(object, method, LT, args);
		} catch (Exception e) {
			//Already handled and logged
		}
		if(lt != null) {
			try{
				if(getCurrentType().compareTo(lt) >= 0) {
					ret = false;
				}
			} catch (IllegalArgumentException e) {
				InternalLogger.e("'network.state' condition requires a 'lt' argument to be one of 'none', 'cell', or 'wifi'.", e);
				return false;
			}
		}
		
		String lte = "";
		try {
			lte = optStringArg(object, method, LTE, args);
		} catch (Exception e) {
			//Already handled and logged
		}
		if(lte != null) {
			try{
				if(getCurrentType().compareTo(lte) > 0) {
					ret = false;
				}
			} catch (IllegalArgumentException e) {
				InternalLogger.e("'network.state' condition requires a 'lte' argument to be one of 'none', 'cell', or 'wifi'.", e);
				return false;
			}
		}
		
		InternalLogger.d("'network.state' condition evaluated to '" + ret + "'.");
		return ret;
	}

	@Override
	public Listener initTrigger(final Runnable callback, String key, String object, String method, Map<String, Object> args) {
		Listener listener = new NetworkStateChangeListener() {
			
			@Override
			public void handle(NetworkStateChangeEvent event) {
				callback.run();
			}
		};
		addListener(listener);
		callback.run();
		return listener;
	}

	@Override
	public void shutdownTrigger(Listener listener, String object, String method, Map<String, Object> args) {
		removeListener(listener);
	}
	
	@Override
	public Map<String, List<String>> getMethodsToRegister() {
		Map<String, List<String>> objects = new HashMap<String, List<String>>();
		List<String> methods = new ArrayList<String>();
		methods.add(ON_STATE_CHANGE);
		methods.add(STATE);
		objects.put(NETWORK, methods);
		
		return objects;
	}

}
