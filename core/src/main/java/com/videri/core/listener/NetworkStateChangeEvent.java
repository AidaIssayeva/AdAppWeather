package com.videri.core.listener;


import com.videri.core.state.NetworkState.Type;

public class NetworkStateChangeEvent implements Event {

	private Type networkType;

	public NetworkStateChangeEvent(Type type) {
		this.networkType = type;
	}
	
	public Type getNetworkType() {
		return networkType;
	}

}
