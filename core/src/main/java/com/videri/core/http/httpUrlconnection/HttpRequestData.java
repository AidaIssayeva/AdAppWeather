package com.videri.core.http.httpUrlconnection;

import com.videri.core.http.httpUrlconnection.body.HttpBody;

import java.util.HashMap;

/**
 * Created by yiminglin on 12/10/15.
 */
public class HttpRequestData {
    private HashMap<String,String> headers;
    private HashMap<String,String> queryParameters;
    private HttpBody bodyData;


    public void setHeaders(HashMap<String,String> headers){
        this.headers = headers;
    }

    public HashMap<String,String> getHeader(){return  headers;}

    //TODO:need to get header as array?
//    public Header[] getHeadersAsArray() {
//        Header[] headerArray = null;
//        if (headers != null) {
//            headerArray = headers.toArray(new Header[headers.size()]);
//        }
//        return headerArray;
//    }

    public void setQueryParameters(HashMap<String,String> params) {
        queryParameters = params;
    }

    public HashMap getQueryParameters(){
        return queryParameters;
    }

    //TODO: get Query Parameters As String
//    public String getQueryParametersAsString() {
//        String queryParamString = "";
//        if (queryParameters != null) {
//            queryParamString = URLEncodedUtils.format(queryParameters, "UTF-8");
//        }
//        return queryParamString;
//    }

    public void setBodyData(HttpBody bodyData){
        this.bodyData = bodyData;
    }

    public HttpBody getBodyData(){
        return bodyData;
    }

    //TODO: check httpEntity class to mimic the same entity
//    public HttpEntity getBodyEntity() {
//        HttpEntity bodyEntity = null;
//        if (bodyData != null) {
//            bodyEntity = bodyData.getEntity();
//        }
//        return bodyEntity;
//    }

    public void addHeader(String name, String value) {
        if (headers == null) {
            headers = new HashMap<>();
        }

        headers.put(name,value);
    }

    public void addQueryParameter(String name,String value ) {
        if (queryParameters == null) {
            queryParameters = new HashMap<String, String>();
        }

        queryParameters.put(name, value);
    }

    public void removeQueryParameter(String name){
        if(queryParameters != null)
            queryParameters.remove(name);
    }

    public void clearHeaders(){
        if(headers != null)
            headers.clear();
    }

    public void clearQueryParameter(){
        if(queryParameters != null){
            queryParameters.clear();
        }
    }

    public void mergeRequestData(HttpRequestData newData) {
        if (newData != null) {
            if (newData.getBodyData() != null) {
                setBodyData(newData.getBodyData());
            }

            //TODO: if a key exists in both maps, the value in map2 will be preserved and the value in map1 lost.
            getHeader().putAll(newData.getHeader());
            getQueryParameters().putAll(newData.getQueryParameters());
        }
    }



}
























