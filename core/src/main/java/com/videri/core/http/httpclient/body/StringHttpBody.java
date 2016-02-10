package com.videri.core.http.httpclient.body;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by yiminglin on 12/16/15.
 */
public class StringHttpBody implements HttpClientBody {
    private HttpEntity entity;
    private static final String UNSUPPORTED_ENCODING = "Generating an HttpEntity failed with UnsupportedEncodingException.";

    public StringHttpBody(String body) {
        try {
            // InternalLogger.d("Creating a String request body.");
            entity = new StringEntity(body, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            // Untestable, UTF-8 is always a supported encoding type
            // InternalLogger.wtf("UTF-8 is not a supported encoding.", e);
            // throw new VDRRuntimeException(UNSUPPORTED_ENCODING, e);
        }
    }

    @Override
    public HttpEntity getEntity() {
        return entity;
    }
}
