package com.videri.core.http.httpclient.handler;

import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientJsonResponseHandler extends HttpClientTextResponseHandler {

    private static final int RESPONSE_SUCCESS_JSON = 2;
    private static final int RESPONSE_FAIL_JSON = 3;


    public HttpClientJsonResponseHandler() {}


    @Override
    protected void processSuccess(String responseText, int statusCode) {
        try {
            Object parsedObject = processText(responseText);
            ResponseData responseObject = new ResponseData(parsedObject, statusCode);
            sendMessage(obtainMessage(RESPONSE_SUCCESS_JSON, responseObject));
        }
        catch (JSONException e) {
            processFailure(e, null);
        }
    }


    @Override
    protected void processFailure(Throwable e, String responseText) {
        Object parsedObject = null;
        try {
            parsedObject = processText(responseText);
        }
        catch (JSONException parseException) {
            // We've already failed, so if we fail to parse the response
            // text properly, just pass the null response object.
        }

        ResponseData responseObject = new ResponseData(e, parsedObject);
        sendMessage(obtainMessage(RESPONSE_FAIL_JSON, responseObject));
    }

    @Override
    protected void handleMessage(Message msg) {
        ResponseData response = (ResponseData)msg.obj;

        switch (msg.what) {
            case RESPONSE_SUCCESS_JSON:
                Object successJson = response.getResponseObject();
                int statusCode = response.getStatusCode();
                if (successJson == null || successJson instanceof JSONObject) {
                    onSuccess((JSONObject)successJson, statusCode);
                }
                else if (successJson instanceof JSONArray) {
                    onSuccess((JSONArray)successJson, statusCode);
                }
                break;
            case RESPONSE_FAIL_JSON:
                Object failureJson = response.getResponseObject();
                Throwable failureException = response.getFailureException();
                if (failureJson == null || failureJson instanceof JSONObject) {
                    onFailure(failureException, (JSONObject)failureJson);
                }
                else if (failureJson instanceof JSONArray) {
                    onFailure(failureException, (JSONArray)failureJson);
                }
                break;
            default:
                super.handleMessage(msg);
        }
    }


    private Object processText(String text) throws JSONException {
        Object parsedObject = null;
        if (text != null && text.length() > 0) {
            parsedObject = HttpClientJsonResponseHandler.parseJson(text);
        }
        return parsedObject;
    }

    public static Object parseJson(String text) throws JSONException {
        Object json = null;
        String str = text.trim();
        if (str.startsWith("{")) {
            json = new JSONObject(str);
        }
        else if (str.startsWith("[")) {
            json = new JSONArray(str);
        }
        else {
            throw new JSONException(text + " is not a valid JSON object or array.");
        }
        return json;
    }


    public void onSuccess(JSONObject responseObject) {
    };

    public void onSuccess(JSONObject responseObject, int statusCode) {
        onSuccess(responseObject);
    };

    public void onSuccess(JSONArray responseArray) {
    };

    public void onSuccess(JSONArray responseArray, int statusCode) {
        onSuccess(responseArray);
    }

    public void onFailure(Throwable e, JSONObject responseObject) {
        onFailure(e);
    }


    public void onFailure(Throwable e, JSONArray responseArray) {
        onFailure(e);
    }

}
