package com.videri.core.http.httpclient.handler;

import android.os.Message;

import org.apache.http.HttpResponse;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientRawResponseHandler extends HttpClientBaseResponseHandler {


    public HttpClientRawResponseHandler() {
    }

    @Override
    public void handleResponse(HttpResponse response) {
        processSuccess(response);
    }

    @Override
    public void handleFailure(Throwable e) {
        processFailure(e);
    }

    @Override
    public void handleDone() {
        processDone();
    }


    protected void processSuccess(HttpResponse response) {
        sendMessage(obtainMessage(RESPONSE_SUCCESS, response));
    }


    protected void processFailure(Throwable e) {
        sendMessage(obtainMessage(RESPONSE_FAIL, e));
    }

    @Override
    protected void processDone() {
        sendMessage(obtainMessage(RESPONSE_FINISH, null));
    }

    @Override
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case RESPONSE_SUCCESS:
                onSuccess((HttpResponse)msg.obj);
                break;
            case RESPONSE_FAIL:
                onFailure((Throwable)msg.obj);
                break;
            case RESPONSE_FINISH:
            default:
                onDone();
        }
    }


    public void onSuccess(HttpResponse response) {
    }


    public void onFailure(Throwable e) {
    }

}
