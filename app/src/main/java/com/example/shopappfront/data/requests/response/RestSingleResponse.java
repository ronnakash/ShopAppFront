package com.example.shopappfront.data.requests.response;

import com.example.shopappfront.data.models.ApplicationModel;
import com.google.gson.reflect.TypeToken;

public class RestSingleResponse<T extends ApplicationModel> extends RestResponse<T> {


    public RestSingleResponse(TypeToken<T> type, String responseString) {
        super(type, responseString);
    }

    public T getData(){
        return (T) super.getParsedResponse();
    }

}
