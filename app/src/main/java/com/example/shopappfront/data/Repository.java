package com.example.shopappfront.data;

import com.example.shopappfront.data.models.ApplicationModel;
import com.example.shopappfront.data.requests.ApplicationRestClient;
import com.example.shopappfront.data.requests.RestRequest;
import com.example.shopappfront.data.requests.RestRequestWithBody;
import com.example.shopappfront.data.requests.response.RestResponse;


public abstract class Repository<T extends ApplicationModel> {
    public ApplicationRestClient arc;

    public Repository() {
        this.arc = new ApplicationRestClient();
    }

    protected void get(RestRequest<RestResponse<T>> request){
        arc.get(request);
    }

    protected void post(RestRequestWithBody<RestResponse<T>> request){
        arc.post(request);
    }

    protected void put(RestRequestWithBody<RestResponse<T>> request){
        arc.put(request);
    }

    protected void delete(RestRequestWithBody<RestResponse<T>> request){
        arc.delete(request);
    }

}