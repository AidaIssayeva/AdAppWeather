package com.videri.core.http.httpclient;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientRequestFactory {
    public static HttpUriRequest createGetRequest(String url, HttpClientRequestData data) {
        HttpGet request = new HttpGet(addParamsToUrl(url, data));
        addHeadersToRequest(data, request);
        return request;
    }


    public static HttpUriRequest createPostRequest(String url, HttpClientRequestData data) {
        HttpPost request = new HttpPost(addParamsToUrl(url, data));
        addHeadersToRequest(data, request);
        addBodyToRequest(data, request);
        return request;
    }


    public static HttpUriRequest createPutRequest(String url, HttpClientRequestData data) {
        HttpPut request = new HttpPut(addParamsToUrl(url, data));
        addHeadersToRequest(data, request);
        addBodyToRequest(data, request);
        return request;
    }


    public static HttpUriRequest createDeleteRequest(String url, HttpClientRequestData data) {
        HttpDelete request = new HttpDelete(addParamsToUrl(url, data));
        addHeadersToRequest(data, request);
        return request;
    }

    private static String addParamsToUrl(String url, HttpClientRequestData params) {
        if (params != null) {
            String paramString = params.getQueryParametersAsString();
            if (paramString != null && paramString.length() > 0) {
                if (url.indexOf("?") < 0) {
                    url += "?" + paramString;
                }
                else {
                    url += "&" + paramString;
                }
            }
        }
        return url;
    }

    private static void addHeadersToRequest(HttpClientRequestData requestData, HttpUriRequest request) {
        if (requestData != null) {
            Header[] headers = requestData.getHeadersAsArray();
            if(headers != null) {
                for(Header h : headers) {
                    ;
                }
            }
            request.setHeaders(headers);
        }
    }

    private static void addBodyToRequest(HttpClientRequestData data, HttpEntityEnclosingRequestBase request) {

        if (data != null) {
            request.setEntity(data.getBodyEntity());
        }
    }
}
