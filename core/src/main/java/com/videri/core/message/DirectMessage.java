package com.videri.core.message;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yiminglin on 12/16/15.
 */
public class DirectMessage {

    public boolean isCloseApps() {
        return isCloseApps;
    }

    public void setIsCloseApps(boolean isCloseApps) {
        this.isCloseApps = isCloseApps;
    }

    public LocalSharedPreferencesData getLocalSharedPreferencesData() {
        return localSharedPreferencesData;
    }

    public void setLocalSharedPreferencesData(LocalSharedPreferencesData localSharedPreferencesData) {
        this.localSharedPreferencesData = localSharedPreferencesData;
    }

    public ArrayList<MaxIdPair> getMaxidPairs() {
        return maxidPairs;
    }

    public void setMaxidPairs(ArrayList<MaxIdPair> maxidPairs) {
        this.maxidPairs = maxidPairs;
    }



    @Override
    public String toString() {
        return "DirectMessage{" +
                "isCloseApps=" + isCloseApps +
                ", localSharedPreferencesData=" + localSharedPreferencesData +
                ", maxidPairs=" + maxidPairs +
                ", nextImage='" + nextImage + '\'' +
                ", devices=" + devices +
                '}';
    }


    @SerializedName("is_close_apps")
    private boolean isCloseApps;

    @SerializedName("local_shared_preferences_data")
    private LocalSharedPreferencesData localSharedPreferencesData;



    @SerializedName("max_id_pairs")
    private ArrayList<MaxIdPair> maxidPairs;

    public String getNextImage() {
        return nextImage;
    }

    public void setNextImage(String nextImage) {
        this.nextImage = nextImage;
    }

    public ArrayList<String> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<String> devices) {
        this.devices = devices;
    }


    @SerializedName("next_image")
    private String nextImage;

    @SerializedName("devices")
    private ArrayList<String> devices;

}
