package com.videri.core.message;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yiminglin on 1/20/16.
 */
public class MaxIdPairs {
    public ArrayList<MaxIdPair> getMaxidPairs() {
        return maxidPairs;
    }

    public void setMaxidPairs(ArrayList<MaxIdPair> maxidPairs) {
        this.maxidPairs = maxidPairs;
    }

    @Override
    public String toString() {
        return "MaxIdPairs{" +
                "maxidPairs=" + maxidPairs +
                '}';
    }

    @SerializedName("max_id_pairs")
    private ArrayList<MaxIdPair> maxidPairs;
}
