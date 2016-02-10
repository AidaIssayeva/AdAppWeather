package com.videri.core.http.httpclient;

import android.content.Context;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

import java.io.InputStream;
import java.security.KeyStore;

/**
 * Created by yiminglin on 12/16/15.
 */
public class HttpClientCore extends DefaultHttpClient {
    private static final int HTTP_PORT = 80;
    private static final int HTTP_SECURE_PORT = 443;

    private Context context = null;
    private int certResId;

    public HttpClientCore() {
    }

    public HttpClientCore(Context context, int certResId) {
        this.context = context;
        this.certResId = certResId;
    }

    //http://transoceanic.blogspot.com/2011/11/android-import-ssl-certificate-and-use.html

    @Override
    protected ClientConnectionManager createClientConnectionManager() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), HTTP_PORT));
        if (context != null) {
            registry.register(new Scheme("https", newSslSocketFactory(), HTTP_SECURE_PORT));
        }
        else {
            registry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), HTTP_SECURE_PORT));
        }
        return new SingleClientConnManager(getParams(), registry);
    }

    private SSLSocketFactory newSslSocketFactory() {
        try {
            KeyStore trusted = KeyStore.getInstance("BKS");
            InputStream in = context.getResources().openRawResource(certResId);
            try {
                trusted.load(in, "password".toCharArray());
            }
            finally {
                in.close();
            }
            SSLSocketFactory sf = new SSLSocketFactory(trusted);
            sf.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            return sf;
        }
        catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
