package com.videri.core.message;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yiminglin on 12/27/15.
 */
public class Cluster {
    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public ArrayList<String> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<String> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "clusterId='" + clusterId + '\'' +
                ", devices=" + devices +
                '}';
    }

    @SerializedName("cluster_id")
    private String clusterId;

    @SerializedName("devices")
    private ArrayList<String> devices;
}
