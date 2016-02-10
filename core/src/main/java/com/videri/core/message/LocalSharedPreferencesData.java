package com.videri.core.message;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yiminglin on 1/12/16.
 */
public class LocalSharedPreferencesData {

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }


    public ArrayList<LiveFeedData> getLiveFeedData() {
        return liveFeedData;
    }

    public void setLiveFeedData(ArrayList<LiveFeedData> liveFeedData) {
        this.liveFeedData = liveFeedData;
    }

    @Override
    public String toString() {
        return "LocalSharedPreferencesData{" +
                "cluster=" + cluster +
                ", liveFeedData=" + liveFeedData +
                '}';
    }

    @SerializedName("cluster")
    private Cluster cluster;



    @SerializedName("live_feed_item")
    private ArrayList<LiveFeedData> liveFeedData;
}
