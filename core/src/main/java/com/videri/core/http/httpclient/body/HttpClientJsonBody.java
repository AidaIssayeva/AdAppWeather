package com.videri.core.http.httpclient.body;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientJsonBody implements HttpClientBody {

    private HttpEntity entity;
    private static final String UNSUPPORTED_ENCODING = "Generating an HttpEntity failed with UnsupportedEncodingException.";

    /**
     * Create a new AKJsonHttpBody using a JSONObject as the data.
     *
     * @param json
     *            The backing JSONObject to use for this body.
     *
     * @version 1.0
     */
    public HttpClientJsonBody(JSONObject json) {
        try {
            entity = new StringEntity(json.toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            // Untestable, UTF-8 is always a supported encoding type
            //throw new VDRRuntimeException(UNSUPPORTED_ENCODING, e);
        }
    }

    @Override
    public HttpEntity getEntity() {
        return entity;
    }

}