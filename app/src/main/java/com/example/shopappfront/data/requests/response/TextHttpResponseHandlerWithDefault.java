package com.example.shopappfront.data.requests.response;

import com.loopj.android.http.TextHttpResponseHandler;

public abstract class TextHttpResponseHandlerWithDefault<T> extends TextHttpResponseHandler {

    public abstract void defaultResponse(T obj);

}
