package com.example.shopappfront.data.requests;

import android.content.Context;

import com.example.shopappfront.data.requests.response.RestResponse;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class RestRequestWithBody<T extends RestResponse> extends RestRequest<T>  {

    StringEntity body;

    public RestRequestWithBody(String endpointUrl, AsyncHttpResponseHandler responseHandler, Context context, StringEntity body) {
        super(endpointUrl, responseHandler, context);
        this.body = body;
    }

    public RestRequestWithBody(String endpointUrl, AsyncHttpResponseHandler responseHandler, Context context, Header[] headers, StringEntity body) {
        super(endpointUrl, responseHandler, headers, context);
        this.body = body;
    }

    public StringEntity getBody() {
        return body;
    }

}
