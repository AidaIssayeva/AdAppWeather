package com.videri.core.http.httpclient;

import com.videri.core.http.httpclient.body.HttpClientBody;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientRequestData {
    private List<Header> headers;
    private List<NameValuePair> queryParameters;
    private HttpClientBody bodyData;


    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }


    public List<Header> getHeaders() {
        return headers;
    }


    public Header[] getHeadersAsArray() {
        Header[] headerArray = null;
        if (headers != null) {
            headerArray = headers.toArray(new Header[headers.size()]);
        }
        return headerArray;
    }


    public void setQueryParameters(List<NameValuePair> params) {
        queryParameters = params;
    }


    public List<NameValuePair> getQueryParameters() {
        return queryParameters;
    }


    public String getQueryParametersAsString() {
        String queryParamString = "";
        if (queryParameters != null) {
            queryParamString = URLEncodedUtils.format(queryParameters, "UTF-8");
        }
        return queryParamString;
    }


    public void setBodyData(HttpClientBody bodyData) {
        this.bodyData = bodyData;
    }


    public HttpClientBody getBodyData() {
        return bodyData;
    }


    public HttpEntity getBodyEntity() {
        HttpEntity bodyEntity = null;
        if (bodyData != null) {
            bodyEntity = bodyData.getEntity();
        }
        return bodyEntity;
    }


    public void addHeader(Header header) {
        if (headers == null) {
            headers = new ArrayList<Header>();
        }

        headers.add(header);
    }


    public void addHeader(String name, String value) {
        addHeader(new BasicHeader(name, value));
    }


    public void addQueryParameter(NameValuePair param) {
        if (queryParameters == null) {
            queryParameters = new ArrayList<NameValuePair>();
        }

        queryParameters.add(param);
    }


    public void addQueryParameter(String name, String value) {
        addQueryParameter(new BasicNameValuePair(name, value));
    }


    public void removeHeader(Header header) {
        if (headers != null) {
            headers.remove(header);
        }
    }


    public void removeQueryParameter(NameValuePair param) {
        if (queryParameters != null) {
            queryParameters.remove(param);
        }
    }


    public void clearHeaders() {
        if (headers != null) {
            headers.clear();
        }
    }


    public void clearQueryParameters() {
        if (queryParameters != null) {
            queryParameters.clear();
        }
    }


    public void mergeRequestData(HttpClientRequestData newData) {
        if (newData != null) {
            if (newData.getBodyData() != null) {
                setBodyData(newData.getBodyData());
            }

            setHeaders(mergeLists(newData.getHeaders(), getHeaders()));
            setQueryParameters(mergeLists(newData.getQueryParameters(), getQueryParameters()));
        }
    }


    private <T> List<T> mergeLists(List<T> dst, List<T> src) {
        Set<T> union = new HashSet<T>();
        if (dst != null) {
            union.addAll(dst);
        }
        if (src != null) {
            union.addAll(src);
        }
        List<T> merged = new ArrayList<T>(union);
        return merged;
    }
}
