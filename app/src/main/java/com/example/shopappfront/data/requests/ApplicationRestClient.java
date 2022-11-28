package com.example.shopappfront.data.requests;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.HttpDelete;
import com.loopj.android.http.RequestParams;

import java.net.URI;
import java.net.URL;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class ApplicationRestClient extends AsyncHttpClient{

    public ApplicationRestClient() {
        super();
    }

    //base api url
    static final String baseUrlString = "http://10.0.2.2:8080/ShopAppServer/api";
    //content type for requests with body
    static final String contentType = "application/json";

    public void get(RestRequest req, RequestParams params) {
        String url = baseUrlString+req.getEndpointUrl();
        Context context = req.getContext();
        AsyncHttpResponseHandler rh = req.getResponseHandler();
        Header[] headers = req.getHeaders();
        super.get(context, url, headers, params, rh);
    }

    public void get(RestRequest req) {
        get(req, new RequestParams());
    }

    public void post(RestRequestWithBody req, RequestParams params) {
        String url = baseUrlString+req.getEndpointUrl();
        Context context = req.getContext();
        AsyncHttpResponseHandler rh = req.getResponseHandler();
        StringEntity reqBody = req.getBody();
        Header[] headers = req.getHeaders();
        super.post(context, url, headers, reqBody, contentType, rh);
    }

    public void post(RestRequestWithBody req) {
        post(req, new RequestParams());
    }

    public void put(RestRequestWithBody req, RequestParams params) {
        String url = baseUrlString+req.getEndpointUrl();
        Context context = req.getContext();
        AsyncHttpResponseHandler rh = req.getResponseHandler();
        StringEntity reqBody = req.getBody();
        Header[] headers = req.getHeaders();
        super.put(context, url, headers, reqBody, contentType, rh);
    }

    public void put(RestRequestWithBody req) {
        put(req, new RequestParams());
    }

    public void delete(RestRequestWithBody req, RequestParams params) {
        String url = baseUrlString+req.getEndpointUrl();
        Context context = req.getContext();
        AsyncHttpResponseHandler rh = req.getResponseHandler();
        StringEntity reqBody = req.getBody();
        Header[] headers = req.getHeaders();

        HttpDelete httpDelete = new HttpDelete(getUrlWithQueryString(true, url, params));
        httpDelete.setEntity(reqBody);
        if (headers != null)
            httpDelete.setHeaders(headers);
        super.sendRequest((DefaultHttpClient) super.getHttpClient(), super.getHttpContext(), httpDelete,
                contentType, rh, context);

//        super.delete(context, url, reqBody, contentType, rh);
    }

    public void delete(RestRequestWithBody req) {
        delete(req, new RequestParams());
    }

}
