package com.videri.adappweather;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.videri.adappweather.openweathermap.OpenWeatherMapApi;
import com.videri.core.http.httpclient.HttpClientAgent;
import com.videri.core.http.httpclient.HttpClientRequestData;
import com.videri.core.http.httpclient.handler.HttpClientJsonResponseHandler;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends Activity {

    private final String TAG = "MainActivity";

    private String publicIdAddress = "";

    private HttpClientAgent mHttpClientAgent = null;

    private Handler mHandler;

    private TextView mTextView;
    private ImageView mImageView;

    private boolean isGreater40 = true;

    //    private final int TEMPERATURE_UPDATE_DELAY = 300000;
    private final int TEMPERATURE_UPDATE_DELAY = 15000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHttpClientAgent = new HttpClientAgent();
        mHandler = new Handler();
        mTextView = (TextView)findViewById(R.id.textView);
        mImageView = (ImageView)findViewById(R.id.imageView);

        mTextView.setText("");
        //   new RetrieveOnlineStatusTask().execute((Void) null);
        // getWeatherInfo("10013");
        mHandler.post(TemperatureScheduler);
    }

    private void getWeatherInfo( String queryData){
        HttpClientRequestData data = new HttpClientRequestData();
        data.addQueryParameter(Constants.OPENWEATHERMAP_APPID, Constants.FORECAST_API_KEY);
        data.addQueryParameter(Constants.OPENWEATHERMAP_QUERY, queryData);
//        "http://api.openweathermap.org/data/2.5/weather?zip=10013,us&APPID=0201780f46c2ab64910049b9c0fdb65d"
        mHttpClientAgent.get(Constants.OPENWEATHERMAP_BASE_URL,data,
                new HttpClientJsonResponseHandler(){

                    @Override
                    public void onSuccess(JSONObject responseJson) {
                        super.onSuccess(responseJson);

                        boolean isGreaterTemp = false;
                        Gson tempGson = new Gson();
                        OpenWeatherMapApi tempGsonObj = tempGson.fromJson(responseJson.toString(), OpenWeatherMapApi.class);

                        Log.v(TAG, tempGsonObj.toString());
                        double fahrenheit = TemperatureConverter.kelvinToFahrenheit(tempGsonObj.getMain().getTemp() );
                        String text = "";
                        if(fahrenheit <= 40){
                            text  = tempGsonObj.getName() + " " + String.format("%.2f",fahrenheit) +" degree " + tempGsonObj.getWeather().get(0).getMain();
                            Log.v(TAG, text);
                            isGreaterTemp = false;
                        }
                        else {

                            text  = tempGsonObj.getName() + " " + String.format("%.2f", fahrenheit) + " degree " + tempGsonObj.getWeather().get(0).getMain();
                            Log.v(TAG, text);

                            isGreaterTemp = true;
                        }

                        if(mTextView.getText().equals("")){
                            mTextView.setText(text);
                        }

                        if(isGreater40 != isGreaterTemp){
                            isGreater40 = isGreaterTemp;
                            if(isGreaterTemp){
                                if (getResources().getConfiguration().orientation ==
                                        Configuration.ORIENTATION_PORTRAIT) {
                                    Picasso.with(getApplicationContext())
                                            .load(R.mipmap.sunv)
                                            .into(mImageView);
                                } else{
                                    Picasso.with(getApplicationContext())
                                            .load(R.mipmap.sunh)
                                            .into(mImageView);

                                }
                            }
                            else {
                                if (getResources().getConfiguration().orientation ==
                                        Configuration.ORIENTATION_PORTRAIT) {
                                    Picasso.with(getApplicationContext())
                                            .load(R.mipmap.snowv)
                                            .into(mImageView);
                                } else{
                                    Picasso.with(getApplicationContext())
                                            .load(R.mipmap.snowh)
                                            .into(mImageView);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, JSONObject responseObject) {
                        super.onFailure(e, responseObject);
                    }

                    @Override
                    public void onDone() {
                        super.onDone();
                    }
                });

    }

    private Runnable TemperatureScheduler = new Runnable(){

        @Override
        public void run() {
            getWeatherInfo("10013,us");
            mHandler.postDelayed(this, TEMPERATURE_UPDATE_DELAY);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if(TemperatureScheduler != null)
            mHandler.removeCallbacks(TemperatureScheduler);
    }

    //getting public Ip address
    class RetrieveOnlineStatusTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... arg0) {
            String ip = "";
            Document doc = null;

            Log.v(TAG,"RetrieveOnlineStatusTask is running");
            try {
                doc = Jsoup.connect("http://www.checkip.org").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ip = doc.getElementById("yourip").select("h1").first().select("span").text();

            return ip;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            publicIdAddress = s;
            // mHttpClientAgent.get();
        }
    }
}
