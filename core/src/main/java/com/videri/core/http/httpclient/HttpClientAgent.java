package com.videri.core.http.httpclient;

import android.util.Log;

import com.videri.core.http.httpclient.handler.HttpClientResponseHandler;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.params.HttpConnectionParams;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientAgent {
    private final String TAG = "VDRHttpAgent";
    private HttpClientRequestData agentRequestData;
    private HttpClientRequestOptions agentRequestOptions;
    private static ExecutorService threadPool;



    public HttpClientAgent() {
        synchronized (HttpClientAgent.class) {
            if(threadPool == null)
                threadPool = Executors.newCachedThreadPool();
        }

        agentRequestData = new HttpClientRequestData();
        agentRequestOptions = new HttpClientRequestOptions();
    }


    public void get(String url, HttpClientResponseHandler responseHandler) {
        get(url, null, null, responseHandler);
    }

    public void get(String url,HttpClientRequestData data, HttpClientResponseHandler responseHandler){
        get(url,data,null,responseHandler);
    }

    public void get(String url, HttpClientRequestData data, HttpClientRequestOptions options, HttpClientResponseHandler responseHandler) {
        HttpUriRequest request = HttpClientRequestFactory.createGetRequest(url, mergeRequestDataWithAgentRequestData(data));
        send(request, responseHandler, options);
    }


    public void post(String url, HttpClientRequestData data, HttpClientResponseHandler responseHandler) {
        post(url, data, null, responseHandler);
    }


    public void post(String url, HttpClientRequestData data, HttpClientRequestOptions options, HttpClientResponseHandler responseHandler) {
        HttpUriRequest request = HttpClientRequestFactory.createPostRequest(url, mergeRequestDataWithAgentRequestData(data));
        send(request, responseHandler, options);
    }


    public void put(String url, HttpClientRequestData data, HttpClientResponseHandler responseHandler) {
        put(url, data, null, responseHandler);
    }


    public void put(String url, HttpClientRequestData data, HttpClientRequestOptions options, HttpClientResponseHandler responseHandler) {
        HttpUriRequest request = HttpClientRequestFactory.createPutRequest(url, mergeRequestDataWithAgentRequestData(data));
        send(request, responseHandler, options);
    }


    public void delete(String url, HttpClientResponseHandler responseHandler) {
        delete(url, null, null, responseHandler);
    }


    public void delete(String url, HttpClientRequestData data, HttpClientRequestOptions options, HttpClientResponseHandler responseHandler) {
        HttpUriRequest request = HttpClientRequestFactory.createDeleteRequest(url, mergeRequestDataWithAgentRequestData(data));
        send(request, responseHandler, options);
    }


    public void send(HttpUriRequest request, HttpClientResponseHandler responseHandler, HttpClientRequestOptions options) {
        threadPool.submit(new Requestor(request, options, responseHandler));
    }


    public HttpResponse getSync(String url) throws IOException {
        return getSync(url, null, null);
    }


    public HttpResponse getSync(String url, HttpClientRequestData data, HttpClientRequestOptions options) throws IOException {
        HttpUriRequest request = HttpClientRequestFactory.createGetRequest(url, mergeRequestDataWithAgentRequestData(data));
        return sendSync(request, options);
    }


    public HttpResponse postSync(String url, HttpClientRequestData data) throws IOException {
        return postSync(url, data, null);
    }


    public HttpResponse postSync(String url, HttpClientRequestData data, HttpClientRequestOptions options) throws IOException {
        HttpUriRequest request = HttpClientRequestFactory.createPostRequest(url, mergeRequestDataWithAgentRequestData(data));
        return sendSync(request, options);
    }


    public HttpResponse putSync(String url, HttpClientRequestData data) throws IOException {
        return putSync(url, data, null);
    }


    public HttpResponse putSync(String url, HttpClientRequestData data, HttpClientRequestOptions options) throws IOException {
        HttpUriRequest request = HttpClientRequestFactory.createPutRequest(url, mergeRequestDataWithAgentRequestData(data));
        return sendSync(request, options);
    }


    public HttpResponse deleteSync(String url) throws IOException {
        return deleteSync(url, null, null);
    }


    public HttpResponse deleteSync(String url, HttpClientRequestData data, HttpClientRequestOptions options) throws IOException {
        HttpUriRequest request = HttpClientRequestFactory.createDeleteRequest(url, mergeRequestDataWithAgentRequestData(data));
        return sendSync(request, options);
    }


    public HttpResponse sendSync(HttpUriRequest request, HttpClientRequestOptions options) throws IOException {
        HttpClient client = getHttpClient(options);
        return client.execute(request);
    }



    public HttpClientRequestData getAgentRequestData() {
        return agentRequestData;
    }


    public HttpClientRequestOptions getAgentRequestOptions() {
        return agentRequestOptions;
    }


    public static boolean isSuccessfulHttpResponse(HttpResponse response) {
        return ((response != null)
                && (response.getStatusLine() != null)
                && (response.getStatusLine().getStatusCode() < HttpStatus.SC_MULTIPLE_CHOICES));
    }

    private class Requestor implements Runnable {

        private HttpUriRequest request;
        private HttpClientRequestOptions options;
        private HttpClientResponseHandler responseHandler;

        public Requestor(HttpUriRequest request, HttpClientRequestOptions options, HttpClientResponseHandler responseHandler) {
            this.request = request;
            this.options = options;
            this.responseHandler = responseHandler;
        }

        @Override
        public void run() {
            try {
                Log.v(TAG + " Requestor", " running now ");
                HttpClient client = getHttpClient(options);
                HttpResponse response = client.execute(request);
                responseHandler.handleResponse(response);
            }
            catch (Exception e) {
                Log.v(TAG + " Requestor", " error " + e.toString());
                responseHandler.handleFailure(e);
            }
            finally {
                responseHandler.handleDone();
            }
        }
    }


    private HttpClientRequestData mergeRequestDataWithAgentRequestData(HttpClientRequestData data) {
        if (data != null) {
            data.mergeRequestData(agentRequestData);
        }
        else {
            data = agentRequestData;
        }
        return data;
    }


    protected HttpClient getHttpClient(HttpClientRequestOptions options) {
        HttpClient agentClient = agentRequestOptions.getHttpClient();

        HttpClient client = (agentClient != null) ? agentClient : HttpClientFactory.getInstance().getDefaultHttpClient();
        if (options != null) {
            if (options.getHttpClient() != null) {
                client = options.getHttpClient();
            }
            HttpConnectionParams.setConnectionTimeout(client.getParams(), options.getTimeout());
        }
        int timeout = 0;
        if(client.getParams() != null) {
            timeout = HttpConnectionParams.getConnectionTimeout(client.getParams());
        }
        if(timeout > 0) {
            ;
        } else {
            ;
        }

        return client;
    }
}
