package com.videri.core.http.httpclient;

import org.apache.http.client.HttpClient;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientRequestOptions {
    private HttpClient client;
    private int timeout;


    public int getTimeout() {
        return timeout;
    }


    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }


    public HttpClient getHttpClient() {
        return client;
    }

    public void setHttpClient(HttpClient client) {
        this.client = client;
    }
}
