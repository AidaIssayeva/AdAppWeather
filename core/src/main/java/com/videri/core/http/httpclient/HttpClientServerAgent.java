package com.videri.core.http.httpclient;

import android.content.Context;

import com.videri.core.http.httpclient.handler.HttpClientResponseHandler;

import org.apache.http.HttpResponse;

import java.io.IOException;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientServerAgent extends HttpClientAgent {
    private final String TAG = "VDRServerAgent";
    //public static final String SESSION_KEY = "X-Ak-Session-Id";
//    protected static final String DEVICE_KEY = "X-Ak-Device-Id";
    protected static final String DEVICE_KEY = "";
    public static final String SESSION_KEY = "session_key1";

    protected static final String CONTENT_TYPE_KEY = "Content-Type";
    protected static final String ACCEPT_KEY = "Accept";
    protected static final String APPLICATION_JSON = "application/json";


    public HttpClientServerAgent() {
        super();

        HttpClientRequestData requestData = getAgentRequestData();

        requestData.addHeader(CONTENT_TYPE_KEY, APPLICATION_JSON);
        requestData.addHeader(ACCEPT_KEY, APPLICATION_JSON);
    }


    public HttpClientServerAgent(Context context, int sslCertificateResourceID) {
        this();

        getAgentRequestOptions()
                .setHttpClient(HttpClientFactory.getInstance().getSSLSelfSignedClient(context, sslCertificateResourceID));
    }


    @Override
    public void get(String endpoint, HttpClientRequestData requestData, HttpClientRequestOptions requestOptions, HttpClientResponseHandler responseHandler) {
        //  super.get(endpoint, setVDRRequestData(requestData), requestOptions, responseHandler);
        super.get(endpoint, setVDRRequestData(requestData), requestOptions, responseHandler);
    }

    @Override
    public void post(String endpoint, HttpClientRequestData requestData, HttpClientRequestOptions requestOptions, HttpClientResponseHandler responseHandler) {
        super.post( endpoint, setVDRRequestData(requestData), requestOptions, responseHandler);
    }


    @Override
    public void put(String endpoint, HttpClientRequestData requestData, HttpClientRequestOptions requestOptions, HttpClientResponseHandler responseHandler) {
        super.put( endpoint, setVDRRequestData(requestData), requestOptions, responseHandler);
    }


    @Override
    public void delete(String endpoint, HttpClientRequestData requestData, HttpClientRequestOptions requestOptions, HttpClientResponseHandler responseHandler) {
        super.delete( endpoint, setVDRRequestData(requestData), requestOptions, responseHandler);
    }


    @Override
    public HttpResponse getSync(String endpoint, HttpClientRequestData requestData, HttpClientRequestOptions requestOptions) throws IOException {
        return super.getSync( endpoint, setVDRRequestData(requestData), requestOptions);
    }


    @Override
    public HttpResponse postSync(String endpoint, HttpClientRequestData requestData, HttpClientRequestOptions requestOptions) throws IOException {
        return super.postSync(endpoint, setVDRRequestData(requestData), requestOptions);
    }

    @Override
    public HttpResponse putSync(String endpoint, HttpClientRequestData requestData, HttpClientRequestOptions requestOptions) throws IOException {
        return super.putSync(endpoint, setVDRRequestData(requestData), requestOptions);
    }


    @Override
    public HttpResponse deleteSync(String endpoint, HttpClientRequestData requestData, HttpClientRequestOptions requestOptions) throws IOException {
        return super.deleteSync(endpoint, setVDRRequestData(requestData), requestOptions);
    }

    private HttpClientRequestData setVDRRequestData(HttpClientRequestData requestData) {
//        if (requestData == null) {
//            requestData = new HttpClientRequestData();
//        }
//        if (VDRSession.getCurrentSession() != null && VDRSession.getCurrentSession().getSessionId() != null) {
//            String sessionId = VDRSession.getCurrentSession().getSessionId();
//            requestData.addHeader(SESSION_KEY, sessionId);
//        }
//        if (VDRDevice.getDeviceToken() != null) {
//            requestData.addHeader(DEVICE_KEY, VDRDevice.getDeviceToken());
//        }
        return requestData;
    }
}
