package com.videri.core.http.httpclient.handler;

import org.apache.http.HttpResponse;

/**
 * Created by yiminglin on 12/16/15.
 */
public interface HttpClientResponseHandler {

    void handleResponse(HttpResponse response);


    void handleFailure(Throwable e);


    void handleDone();
}
