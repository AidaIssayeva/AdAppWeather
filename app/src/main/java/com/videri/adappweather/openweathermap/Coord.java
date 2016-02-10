package com.videri.adappweather.openweathermap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yiminglin on 1/14/16.
 */
public class Coord {

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    @SerializedName("lon")
    private double longitude;

    @SerializedName("lat")
    private double latitude;

}
