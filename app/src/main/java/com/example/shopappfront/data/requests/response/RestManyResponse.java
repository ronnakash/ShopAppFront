package com.example.shopappfront.data.requests.response;

import com.example.shopappfront.data.models.ApplicationModelWithId;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class RestManyResponse<T extends ApplicationModelWithId> extends RestResponse<T> {
    List<T> data;

    public RestManyResponse(TypeToken<List<T>> typeOfMany, String responseString) {
        super(typeOfMany, responseString);
    }

    public List<T> getData(){
        return (List<T>) super.getParsedResponse();
    }

}