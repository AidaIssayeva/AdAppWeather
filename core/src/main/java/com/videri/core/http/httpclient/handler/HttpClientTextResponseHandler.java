package com.videri.core.http.httpclient.handler;

import android.os.Message;

import com.videri.core.http.httpclient.HttpClientEntityParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;

import java.io.IOException;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientTextResponseHandler extends HttpClientBaseResponseHandler {

    public HttpClientTextResponseHandler() {
    }

    @Override
    public void handleResponse(HttpResponse response) {
        processResponse(response);
    }

    @Override
    public void handleFailure(Throwable e) {
        processFailure(e, null);
    }

    @Override
    public void handleDone() {
        processDone();
    }

    protected void processResponse(HttpResponse response) {
        StatusLine responseStatus = response.getStatusLine();
        String responseText = null;

        // Parse the response entity if available, no matter the status code.
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            try {
                responseText = HttpClientEntityParser.parseString(response.getEntity());
            }
            catch (IOException e) {
                processFailure(e, null);
            }
        }

        if (isSuccessfulHttpResponse(response)) {
            processSuccess(responseText, responseStatus.getStatusCode());
        }
        else {
            processFailure(new HttpResponseException(responseStatus.getStatusCode(), responseStatus.getReasonPhrase()), responseText);
        }
    }


    protected void processSuccess(String responseText, int statusCode) {
        ResponseData responseObject = new ResponseData(responseText, statusCode);
        sendMessage(obtainMessage(RESPONSE_SUCCESS, responseObject));
    }


    protected void processFailure(Throwable e, String responseText) {
        ResponseData responseData = new ResponseData(e, responseText);
        sendMessage(obtainMessage(RESPONSE_FAIL, responseData));
    }

    @Override
    protected void processDone() {
        sendMessage(obtainMessage(RESPONSE_FINISH, null));
    }

    @Override
    protected void handleMessage(Message msg) {
        ResponseData response = (ResponseData)msg.obj;
        switch (msg.what) {
            case RESPONSE_SUCCESS:
                Object responseText = response.getResponseObject();
                int statusCode = response.getStatusCode();
                onSuccess((String)responseText, statusCode);
                break;
            case RESPONSE_FAIL:
                Object failureText = response.getResponseObject();
                Throwable failureException = response.getFailureException();
                onFailure(failureException, (String)failureText);
                break;
            case RESPONSE_FINISH:
            default:
                onDone();
        }
    }


    public void onSuccess(String responseText) {
        //Put log here
    }


    public void onSuccess(String responseText, int statusCode) {
        onSuccess(responseText);
        //Put log here
    }


    public void onFailure(Throwable e) {
        //Put log here
    }

    public void onFailure(Throwable e, String responseText) {
        onFailure(e);
    }

}
