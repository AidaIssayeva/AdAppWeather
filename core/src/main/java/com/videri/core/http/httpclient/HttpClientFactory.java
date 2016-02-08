package com.videri.core.http.httpclient;

import android.content.Context;

import org.apache.http.client.HttpClient;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientFactory {
    protected static HttpClientFactory _instance;
    protected ThreadLocal<HttpClient> _client = new ThreadLocal<HttpClient>();
    protected ThreadLocal<HttpClient> _secureClient = new ThreadLocal<HttpClient>();

    public HttpClient getDefaultHttpClient() {
        if(_client.get() == null)
            _client.set(new HttpClientCore());
        return _client.get();
    }

    public HttpClient getSSLSelfSignedClient(Context context, int certResId) {
        if(_secureClient.get() == null)
            _secureClient.set(new HttpClientCore(context, certResId));
        return _secureClient.get();
    }

    public static HttpClientFactory getInstance() {
        if (_instance == null) {
            _instance = new HttpClientFactory();
        }
        return _instance;
    }
}
