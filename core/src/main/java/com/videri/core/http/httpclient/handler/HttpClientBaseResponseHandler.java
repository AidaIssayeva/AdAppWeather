package com.videri.core.http.httpclient.handler;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.videri.core.http.httpclient.HttpClientAgent;

import org.apache.http.HttpResponse;

/**
 * Created by yiminglin on 12/16/15.
 */
public abstract class HttpClientBaseResponseHandler implements HttpClientResponseHandler {


    protected static final int RESPONSE_FAIL = -1;


    protected static final int RESPONSE_FINISH = 0;


    protected static final int RESPONSE_SUCCESS = 1;

    private Handler threadHandler;


    @SuppressLint("HandlerLeak")
    // As long as the handler doesn't reference anything, it won't leak.
    protected HttpClientBaseResponseHandler() {
        if (Looper.myLooper() != null) {
            threadHandler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    HttpClientBaseResponseHandler.this.handleMessage(msg);
                }
            };
        }
    }


    protected boolean isSuccessfulHttpResponse(HttpResponse response) {
        return HttpClientAgent.isSuccessfulHttpResponse(response);
    }


    protected Message obtainMessage(int what, Object data) {
        Message msg = null;
        if (threadHandler != null) {
            msg = threadHandler.obtainMessage(what, data);
        }
        else {
            msg = Message.obtain();
            msg.what = what;
            msg.obj = data;
        }
        return msg;
    }


    protected void sendMessage(Message msg) {
        if (threadHandler != null) {
            threadHandler.sendMessage(msg);
        }
        else {
            handleMessage(msg);
        }
    }


    protected abstract void processDone();


    protected abstract void handleMessage(Message msg);



    protected class ResponseData {

        private int statusCode;
        private Object responseObject;
        private Throwable failureException;

        public ResponseData(Object responseObject, int statusCode) {
            this.statusCode = statusCode;
            this.responseObject = responseObject;
        }

        public ResponseData(Throwable failureException, Object responseObject) {
            this.failureException = failureException;
            this.responseObject = responseObject;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public Object getResponseObject() {
            return responseObject;
        }

        public Throwable getFailureException() {
            return failureException;
        }
    }



    public void onDone() {
    }

}

