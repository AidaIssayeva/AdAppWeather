package com.videri.core.json;

import com.google.gson.Gson;
import com.videri.core.message.DirectMessage;
import com.videri.core.message.LocalSharedPreferencesData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yiminglin on 12/14/15.
 */
public class JSONMsgHandler {

    private static final String TAG = "JSONMsgHandler";
    public static String NFL_APP = "nflApp";
    public static String INTAGRAM_APP = "instagram";
    public static String DEVICE_ID = "deviceId";
    public static String DEVICE_IDS = "deviceIds";
    public static String CHANNEL = "channel";
    public static String INSTAGRAM_ID = "instagram_id";
    public static String APP_TYPE = "appType";
    public static String POSITION = "position";
    public static String SKIP = "skip";
    public static String[] type = {"nfl","instagram"};
    public static final String NEXT_MAX_ID = "next_max_id";
    public static final String NEXT_MAX_IDS = "next_max_ids";
    public static final String NEXT_IMAGE = "next_image";
    public static final String COUNTS = "counts";

    public static JSONObject JsonMsgGenerator(String appType, String dId, String msg){
        JSONObject jsonData = new JSONObject();
        try {

            jsonData.put(APP_TYPE,appType);
            jsonData.put(DEVICE_ID,dId);
            jsonData.put(INSTAGRAM_ID,msg);

        }
        catch (JSONException e){

        }

        return jsonData;
    }

    public static JSONObject JsonMsgGenerator(
            String appType, String deviceId, String instagramId,String nextMaxId,String counts){
        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put(APP_TYPE,appType);
            jsonData.put(DEVICE_ID,deviceId);
            jsonData.put(INSTAGRAM_ID,instagramId);
            jsonData.put(NEXT_MAX_ID, nextMaxId);
            jsonData.put(COUNTS, counts );
        }
        catch (JSONException e){

        }

        return jsonData;
    }


    public static JSONObject JsonMsgGenerator(
            String appType, String[] deviceIds, String instagramId,String[] nextMaxIds,String counts){
        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put(APP_TYPE,appType);
            jsonData.put(DEVICE_IDS,getJSONArray(deviceIds) );
            jsonData.put(INSTAGRAM_ID,instagramId);
            jsonData.put(NEXT_MAX_IDS, getJSONArray(nextMaxIds));
            jsonData.put(COUNTS, counts );

        }
        catch (JSONException e){

        }
        return jsonData;
    }

    public static JSONObject JsonChannelMsgGenerator(
            String appType, String instagramId,String nextMaxId,String counts) {

        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put(APP_TYPE, appType);
            jsonData.put(INSTAGRAM_ID, instagramId);
            jsonData.put(NEXT_MAX_ID, nextMaxId);
            jsonData.put(COUNTS, counts);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public static JSONObject JsonChannelMsgGenerator(
            String appType, String instagramId,String nextImage) {

        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put(APP_TYPE, appType);
            jsonData.put(INSTAGRAM_ID, instagramId);
            jsonData.put(NEXT_IMAGE, nextImage);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public static JSONObject JSONMaxIdPairs(ArrayList<String> deviceIds, ArrayList<String> maxIds){
        JSONObject JsonObjectpair = new JSONObject();
       // jsonData
        try {
            JsonObjectpair.put("max_id_pairs",maxIdsPairsArray(deviceIds,maxIds));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JsonObjectpair;
    }

    public static JSONObject JSONDirectMsg(ArrayList<String> deviceIds, ArrayList<String> maxIds,
                                           LocalSharedPreferencesData localSharedPreferencesData,boolean isCloseApp){
        JSONObject JsonObjectpair = new JSONObject();
        // jsonData
        try {
            if(localSharedPreferencesData != null) {
                Gson gson = new Gson();
                String jsonRepresentation = gson.toJson(localSharedPreferencesData);
                JSONObject obj = new JSONObject(jsonRepresentation);
//                Log.v(TAG, jsonRepresentation);
                JsonObjectpair.put("local_shared_preferences_data", obj);
            }
            JsonObjectpair.put("max_id_pairs",maxIdsPairsArray(deviceIds,maxIds));
            JsonObjectpair.put("is_close_apps",isCloseApp);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JsonObjectpair;
    }


    public static JSONObject nexImageMsg(String [] devices, String nextImage){
        JSONObject json = new JSONObject();
        try {
            json.put("next_image",nextImage);
            json.put("devices",getJSONArray(devices));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }



    public static JSONArray maxIdsPairsArray(ArrayList<String> deviceIds, ArrayList<String> maxIds) throws Exception {

        JSONArray jsonArray = new JSONArray();
        if(deviceIds.size() <= maxIds.size()){
            for(int i = 0; i < deviceIds.size(); i++){
//                arr.put(deviceIds.get(i),maxIds.get(i));
                JSONObject arrObject = new JSONObject();
                arrObject.put("device_id",deviceIds.get(i));
                arrObject.put("max_id",maxIds.get(i));
                jsonArray.put(arrObject);
            }
        }
        else{
            throw new Exception("not enough Max ids. ");
        }


        return jsonArray;
    }

    public static JSONArray getJSONArray( String[] data){
        JSONArray arr = new JSONArray();
        for(int i = 0; i < data.length; i++){
            arr.put(data[i]);
        }
        return arr;
    }

    public static String applicationType(String jsonData){

        try{
            JSONObject JsonMessage = new JSONObject(jsonData);
            if(!JsonMessage.has(APP_TYPE))
                return null;
            for(int i = 0; i < type.length; i++){
                if(type[i].equals(INTAGRAM_APP))
                    return INTAGRAM_APP;
                else if(type[i].equals(NFL_APP))
                    return NFL_APP;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String messageContent(String jsonData){
        String content = "";
        try{
            JSONObject JsonMessage = new JSONObject(jsonData);
            String conent = JsonMessage.getString(APP_TYPE);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }

    public static DirectMessage getVideriMsg(String jsonData){
        Gson gson = new Gson();

        DirectMessage gsonObj = gson.fromJson(jsonData, DirectMessage.class);

        return gsonObj;
    }
}
