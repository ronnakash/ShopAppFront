package com.example.shopappfront.data.requests.response;

import com.example.shopappfront.data.models.ApplicationModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class RestResponse <T extends ApplicationModel>{
    Gson gson;
    TypeToken typeToken;
    String responseString;

    public RestResponse(TypeToken type, String responseString) {
        gson = new Gson();
        typeToken = type;
        this.responseString = responseString;
    }

    protected Object getParsedResponse(){
        return gson.fromJson(responseString, typeToken.getType());
    }

}
