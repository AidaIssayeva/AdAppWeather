package com.videri.adappweather;

/**
 * Created by yiminglin on 1/13/16.
 */
public class Constants {
    public static String FORECAST_API_KEY = "0201780f46c2ab64910049b9c0fdb65d"; //

    public static String INFODB_API_KEY = "7c3d85665769d75ec1951e18931df8d39a11be6237fa349c6e66a42c646d2d3b";


    public static String OPENWEATHERMAP_BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    public static String OPENWEATHERMAP_QUERY = "q";
    public static String OPENWEATHERMAP_ZIP = "zip";
    public static String OPENWEATHERMAP_APPID = "APPID";
    //http://api.ipinfodb.com/v3/ip-city/?key=7c3d85665769d75ec1951e18931df8d39a11be6237fa349c6e66a42c646d2d3b&ip=74.125.45.100

    //api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID=0201780f46c2ab64910049b9c0fdb65d
    //api.openweathermap.org/data/2.5/weather?q={new york},us&APPID=0201780f46c2ab64910049b9c0fdb65d

    //by zip code
    //http://api.openweathermap.org/data/2.5/weather?zip=10013,us&APPID=0201780f46c2ab64910049b9c0fdb65d
    String[][] locations = {{"40.7127", "-74.0059"},{"New York", "NY"}};
}
