package com.example.shopappfront.data.requests;

import android.content.Context;

import com.example.shopappfront.data.AuthenticationRepository;
import com.example.shopappfront.data.models.ApplicationModel;
import com.example.shopappfront.data.requests.response.RestResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.entity.StringEntity;

public class RestRequestBuilder<T extends ApplicationModel> {

    private StringEntity body;
    private String endpointUrl;
    private AsyncHttpResponseHandler responseHandler;
    private Context context;
    private Header[] headers;

    public RestRequestBuilder() {
    }

    public RestRequestBuilder<T> setBody(T entity) {
        this.body = parseBody(entity);
        return this;
    }

    public RestRequestBuilder<T> setUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
        return this;
    }


    public RestRequestBuilder<T> setResponseHandler(AsyncHttpResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        return this;
    }

    public RestRequestBuilder<T> setContext(Context context) {
        this.context = context;
        return this;
    }

    public RestRequestBuilder<T> setHeaders(Header[] headers) {
        this.headers = headers;
        return this;
    }

    public RestRequest<RestResponse<T>> build() {
        if (body == null) {
            if (headers == null)
                return new RestRequest<>(endpointUrl, responseHandler,getUserAuthHeader(), context);
            return new RestRequest<>(endpointUrl, responseHandler, headers, context);
        }
        if (headers == null)
            return new RestRequestWithBody<>(endpointUrl, responseHandler, context, getUserAuthHeader(), body);
        return new RestRequestWithBody<>(endpointUrl, responseHandler, context, headers, body);
    }

    private static Header[] getUserAuthHeader() {
        String token = AuthenticationRepository.getInstance().getUserToken();
        if (token == null)
            return null;
        Header[] headers = new Header[] { new Header() {
            @Override
            public HeaderElement[] getElements() throws ParseException {
                return new HeaderElement[0];
            }

            @Override
            public String getName() {
                return "Authorization";
            }

            @Override
            public String getValue() {
                return token;
            }
        }};
        return headers;
    }

    private StringEntity parseBody(T entity) {
        Gson gson = new Gson();
        TypeToken<T> typeToken = new TypeToken<T>() {};
        try {
            String json = gson.toJson(entity, typeToken.getType());
            return new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
