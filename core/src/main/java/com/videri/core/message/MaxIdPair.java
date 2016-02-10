package com.videri.core.message;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yiminglin on 1/19/16.
 */
public class MaxIdPair {

    public String getMaxId() {
        return maxId;
    }

    public void setMaxId(String maxId) {
        this.maxId = maxId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "MaxIdPair{" +
                "maxId='" + maxId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }

    @SerializedName("max_id")
    private String maxId;
    @SerializedName("device_id")
    private String deviceId;
}
