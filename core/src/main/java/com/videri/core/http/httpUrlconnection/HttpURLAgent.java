package com.videri.core.http.httpUrlconnection;

import android.util.Log;

import com.videri.core.http.httpUrlconnection.handler.HttpAsyncResponseHandler;
import com.videri.core.http.httpUrlconnection.handler.HttpResponseHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yiminglin on 12/10/15.
 */
public class HttpURLAgent {
    private final String TAG = "HttpURLAgent";
    private HttpRequestData agentRequestData;
    private HttpRequestOptions agentRequestOptions;
    private static ExecutorService threadPool;

    public HttpURLAgent(){
        synchronized (HttpURLAgent.class){
            if(threadPool == null){
                threadPool = Executors.newCachedThreadPool();
            }

            agentRequestData = new HttpRequestData();
            agentRequestOptions = new HttpRequestOptions();

        }
    }

    public void get(final String url, HttpResponseHandler responseHandler) {

        get(url, null, false, responseHandler);
    }

//    public static HttpURLRequest get(final CharSequence baseUrl,
//                                  final boolean encode, final Object... params) {
//        String url = append(baseUrl, params);
//        return get(encode ? encode(url) : url);
//    }

    public void get( String url, boolean encode,  HttpResponseHandler responseHandler, Object... param){
        HttpURLRequest request = HttpURLRequest.get(url, encode, param);
        send(request, responseHandler);
    }



    public void get(String url, Map<?,?> params, boolean encode, HttpResponseHandler responseHandler) {
        //HttpUriRequest request = HttpRequestFactory.createDeleteRequest(url, mergeRequestDataWithAgentRequestData(data));
        HttpURLRequest request = HttpURLRequest.get(url, params, encode);
        send(request, responseHandler);
    }


    public void post(String url, Map<?,?> data, boolean encode, HttpResponseHandler responseHandler) {
        post(url, data,encode, null, responseHandler);
    }


    public void post(String url, Map<?,?> data, boolean encode,HttpRequestOptions options, HttpResponseHandler responseHandler) {
        //HttpUriRequest request = HttpRequestFactory.createDeleteRequest(url, mergeRequestDataWithAgentRequestData(data));
        HttpURLRequest httpRequest= HttpURLRequest.post(url, data, encode);
        send(httpRequest, responseHandler);
    }


    public void put(String url, HttpRequestData data, HttpResponseHandler responseHandler) {
        //HttpUriRequest request = HttpRequestFactory.createDeleteRequest(url, mergeRequestDataWithAgentRequestData(data));
        put(url, data, null, responseHandler);
    }


    public void put(String url, HttpRequestData data, HttpRequestOptions options, HttpResponseHandler responseHandler) {
        //HttpUriRequest request = HttpRequestFactory.createDeleteRequest(url, mergeRequestDataWithAgentRequestData(data));
        HttpUriRequest request = null; //TODO: fit it
        send(request, responseHandler, options);
    }


    public void delete(String url, HttpResponseHandler responseHandler) {
        delete(url, null, null, responseHandler);
    }


    public void delete(String url, HttpRequestData data, HttpRequestOptions options, HttpResponseHandler responseHandler) {
        //HttpUriRequest request = HttpRequestFactory.createDeleteRequest(url, mergeRequestDataWithAgentRequestData(data));
        HttpUriRequest request = null; //TODO: fit it
        send(request, responseHandler, options);
    }


    public void send(HttpUriRequest request, HttpResponseHandler responseHandler, HttpRequestOptions options) {
        threadPool.submit(new Requestor(request, options, responseHandler));
    }

    public void send(HttpURLRequest request, HttpResponseHandler responseHandler) {
        threadPool.submit(new Requestor(request, responseHandler));
    }

    public HttpAsyncResponseHandler getSync(String url) throws IOException {
        return getSync(url, null, null);
    }


    public HttpAsyncResponseHandler getSync(String url, HttpRequestData data, HttpRequestOptions options) throws IOException {
        // HttpUriRequest request = HttpRequestFactory.createGetRequest(url, mergeRequestDataWithAgentRequestData(data));
        // return sendSync(request, options);
        return null;
    }


    public HttpAsyncResponseHandler postSync(String url, HttpRequestData data) throws IOException {
        return postSync(url, data, null);
    }


    public HttpAsyncResponseHandler postSync(String url, HttpRequestData data, HttpRequestOptions options) throws IOException {
//        HttpUriRequest request = HttpRequestFactory.createPostRequest(url, mergeRequestDataWithAgentRequestData(data));
       // return sendSync(request, options);
        return null;
    }


    public HttpAsyncResponseHandler putSync(String url, HttpRequestData data) throws IOException {
        return putSync(url, data, null);
    }


    public HttpAsyncResponseHandler putSync(String url, HttpRequestData data, HttpRequestOptions options) throws IOException {
      //  HttpUriRequest request = HttpRequestFactory.createPutRequest(url, mergeRequestDataWithAgentRequestData(data));
        //return sendSync(request, options);
        return null;
    }


    public HttpAsyncResponseHandler deleteSync(String url) throws IOException {
        return deleteSync(url, null, null);
    }


    public HttpAsyncResponseHandler deleteSync(String url, HttpRequestData data, HttpRequestOptions options) throws IOException {
       // HttpUriRequest request = HttpRequestFactory.createDeleteRequest(url, mergeRequestDataWithAgentRequestData(data));
       // return sendSync(request, options);
        return null;
    }


    public HttpAsyncResponseHandler sendSync(HttpUriRequest request, HttpRequestOptions options) throws IOException {
       // HttpClient client = getHttpClient(options);
        //return client.execute(request);
        return null;
    }




    public HttpRequestData getAgentRequestData() {
        return agentRequestData;
    }


    public HttpRequestOptions getAgentRequestOptions() {
        return agentRequestOptions;
    }




    private class Requestor implements Runnable {

        private HttpUriRequest UriRequest;
        private HttpURLRequest request;
        private HttpRequestOptions options;
        private HttpResponseHandler responseHandler;

        public Requestor(HttpUriRequest request, HttpRequestOptions options, HttpResponseHandler responseHandler) {
            this.UriRequest = request;
            this.options = options;
            this.responseHandler = responseHandler;
        }

        public Requestor(HttpURLRequest request, HttpResponseHandler responseHandler) {
            this.request = request;
            this.options = null;
            this.responseHandler = responseHandler;
        }

        @Override
        public void run() {
            try {
                Log.v(TAG + " Requestor", "Running now ");

                String response =   request.body();
       //         HttpURLRequest httpRequest =
//                HttpClient client = getHttpClient(options);
//                HttpAsyncResponseHandler response = client.execute(request);
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







    private HttpRequestData mergeRequestDataWithAgentRequestData(HttpRequestData data) {
        if (data != null) {
            data.mergeRequestData(agentRequestData);
        }
        else {
            data = agentRequestData;
        }
        return data;
    }




}

