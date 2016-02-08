package com.videri.core.http.httpUrlconnection.handler;

/**
 * Created by yiminglin on 12/10/15.
 */
public interface HttpResponseHandler {
    void handleResponse(String response);


    void handleFailure(Throwable e);


    void handleDone();
}
