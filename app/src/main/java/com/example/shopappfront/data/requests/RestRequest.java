package com.example.shopappfront.data.requests;

import android.content.Context;

import com.example.shopappfront.data.requests.response.RestResponse;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class RestRequest<T extends RestResponse> {

    String endpointUrl;
    public AsyncHttpResponseHandler responseHandler;
    Context context;
    Header[] headers;

    public RestRequest(String endpointUrl, AsyncHttpResponseHandler responseHandler, Context context) {
        this.endpointUrl = endpointUrl;
        this.responseHandler = responseHandler;
        this.context = context;
    }

    public RestRequest(String endpointUrl, AsyncHttpResponseHandler responseHandler, Header[] headers, Context context) {
        this(endpointUrl, responseHandler, context);
        this.headers = headers;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public AsyncHttpResponseHandler getResponseHandler() {
        return responseHandler;
    }

    public Context getContext() {
        return context;
    }

    public Header[] getHeaders() {
        return headers;
    }


}
