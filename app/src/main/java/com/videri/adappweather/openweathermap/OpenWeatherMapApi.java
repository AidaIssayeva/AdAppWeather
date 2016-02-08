package com.videri.adappweather.openweathermap;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yiminglin on 1/14/16.
 */
public class OpenWeatherMapApi {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }



    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }


    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "OpenWeatherMapApi{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                ", weather=" + weather +
                ", coord=" + coord +
                ", main=" + main +
                '}';
    }

    @SerializedName("id")
    private int id;


    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private int cod;

    @SerializedName("weather")
    private ArrayList<Weather> weather;

    @SerializedName("coord")
    private Coord coord;

    @SerializedName("main")
    private Main main;
}
