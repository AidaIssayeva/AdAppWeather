package com.videri.core.http.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientEntityParser {

    // TODO: User-defined String encoding.

    private static HttpClientEntityParser _instance;

    private HttpClientEntityParser() {
        // Cannot use this constructor!
    }

    private static HttpClientEntityParser getInstance() {
        if (_instance == null) {
            _instance = new HttpClientEntityParser();
        }
        return _instance;
    }


    protected static void setInstance(HttpClientEntityParser instance) {
        _instance = instance;
    }


    public static JSONObject parseJSONObject(HttpEntity entity) throws IOException, JSONException {
        return getInstance().instanceParseJSONObject(entity);
    }


    public static JSONArray parseJSONArray(HttpEntity entity) throws IOException, JSONException {
        return getInstance().instanceParseJSONArray(entity);
    }


    public static String parseString(HttpEntity entity) throws IOException {
        return getInstance().instanceParseString(entity);
    }


    public static String parseString(HttpEntity entity, String charset) throws IOException {
        return getInstance().instanceParseString(entity, charset);
    }

    private JSONObject instanceParseJSONObject(HttpEntity entity) throws JSONException, IOException {
        JSONObject entityObject = null;
        if (entity != null) {
            entityObject = new JSONObject(instanceParseString(entity));
        }
        return entityObject;
    }

    private JSONArray instanceParseJSONArray(HttpEntity entity) throws JSONException, IOException {
        JSONArray entityArray = null;
        if (entity != null) {
            entityArray = new JSONArray(instanceParseString(entity));
        }
        return entityArray;
    }

    private String instanceParseString(HttpEntity entity) throws IOException {
        return instanceParseString(entity, "UTF-8");
    }

    private String instanceParseString(HttpEntity entity, String defaultCharset) throws IOException {
        String entityString = null;
        if (entity != null) {
            entityString = EntityUtils.toString(entity, defaultCharset);
            entity.consumeContent();
        }
        return entityString;
    }
}
