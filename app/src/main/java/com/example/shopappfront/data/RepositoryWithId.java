package com.example.shopappfront.data;

import android.content.Context;

import com.example.shopappfront.data.adapters.ModelAdapter;
import com.example.shopappfront.data.models.ApplicationModelWithId;
import com.example.shopappfront.data.requests.RestRequest;
import com.example.shopappfront.data.requests.RestRequestBuilder;
import com.example.shopappfront.data.requests.RestRequestWithBody;
import com.example.shopappfront.data.requests.response.RestResponse;
import com.example.shopappfront.data.requests.response.TextHttpResponseHandlerWithDefault;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryWithId<T extends ApplicationModelWithId> extends Repository<T>{

    protected List<T> models;
    private List<ModelAdapter<T>> adapters;
    protected String baseUrl;

    public RepositoryWithId(String baseUrl) {
        super();
        this.baseUrl = baseUrl;
        models = new ArrayList<>();
        adapters = new ArrayList<>();
    }

    public void addAdapter(ModelAdapter<T> adapter){
        if (!adapters.contains(adapter))
            adapters.add(adapter);
    }

    public void removeAdapter(ModelAdapter<T> adapter){
        if (adapters.contains(adapter))
            adapters.remove(adapter);
    }

    public void notifyAdapters(){
        for (ModelAdapter<T> adapter : adapters){
            if(adapter != null){
                adapter.setModels(models);
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void notifyAdaptersChangedAt(int i){
        for (ModelAdapter<T> adapter : adapters){
            if(adapter != null){
                adapter.setModels(models);
                adapter.notifyItemChanged(i);
            }
        }
    }

    public void notifyAdaptersRemovedAt(int i){
        for (ModelAdapter<T> adapter : adapters){
            if(adapter != null){
                adapter.setModels(models);
                adapter.notifyItemRemoved(i);
            }
        }
    }

    public void notifyAdaptersAddedAt(int i){
        for (ModelAdapter<T> adapter : adapters){
            if(adapter != null){
                adapter.setModels(models);
                adapter.notifyItemInserted(i);
            }
        }
    }

    public void setModels(List<T> models) {
        this.models = models;
        notifyAdapters();
    }




    public void getAll(TextHttpResponseHandlerWithDefault<List<T>> responseHandler, Context context){
        if (models != null && models.size() != 0)
            responseHandler.defaultResponse(models);
        else
            get(getAllRequest(responseHandler, context));
    }


    public T getById(int id) {
        for (T model : models){
            if (model.getId() == id)
                return model;
        }
        throw new RuntimeException();
    }

    public void addNewModel(T model){
        models.add(model);
        notifyAdaptersAddedAt(models.size()-1);
    }

    public void updateModel(T model){
        for(int i = 0; i < models.size(); i++){
            if (models.get(i).getId() == model.getId() ){
                models.set(i, model);
                notifyAdaptersChangedAt(i);
                break;
            }
        }
    }

    public void removeModel(T model){
        for(int i = 0; i < models.size(); i++){
            if (models.get(i).getId() == model.getId() ){
                models.remove(i);
                notifyAdaptersRemovedAt(i);
                break;
            }
        }

    }

    @Override
    protected void get(RestRequest<RestResponse<T>> request){
        super.get(request);
    }

    public void create(T model, TextHttpResponseHandler responseHandler, Context context) {
        super.put(createRequest(model, responseHandler, context));
    }

    public void delete(T model, TextHttpResponseHandler responseHandler, Context context){
        if(responseHandler instanceof TextHttpResponseHandlerWithDefault && model.getId()==-1){
            ((TextHttpResponseHandlerWithDefault<T>) responseHandler).defaultResponse(model);
        } else
            super.delete(deleteByIdRequest(model, responseHandler, context));
    }

    public void update(T model, TextHttpResponseHandler responseHandler, Context context) {
        super.post(updateRequest(model, responseHandler, context));
    }


    protected RestRequest<RestResponse<T>> getAllRequest(TextHttpResponseHandler responseHandler, Context context){
        return new RestRequestBuilder<T>()
                .setUrl(baseUrl)
                .setContext(context)
                .setResponseHandler(responseHandler)
                .build();
    }

//    protected RestRequest<RestResponse<T>> getByIdRequest(int id, TextHttpResponseHandler responseHandler, Context context) {
//        return new RestRequestBuilder<T>()
//                .setUrl(baseUrl + "/" + id)
//                .setContext(context)
//                .setResponseHandler(responseHandler)
//                .build();
//    }

    protected RestRequestWithBody<RestResponse<T>> updateRequest(T model, TextHttpResponseHandler responseHandler, Context context) {
        return (RestRequestWithBody<RestResponse<T>>) new RestRequestBuilder<T>()
                .setUrl(baseUrl)
                .setContext(context)
                .setResponseHandler(responseHandler)
                .setBody(model)
                .build();
    }

    protected RestRequestWithBody<RestResponse<T>> createRequest(T model, TextHttpResponseHandler responseHandler, Context context) {
        return (RestRequestWithBody<RestResponse<T>>) new RestRequestBuilder<T>()
                .setUrl(baseUrl)
                .setContext(context)
                .setResponseHandler(responseHandler)
                .setBody(model)
                .build();
    }

    protected RestRequestWithBody<RestResponse<T>> deleteByIdRequest(T model, TextHttpResponseHandler responseHandler, Context context) {
        return (RestRequestWithBody<RestResponse<T>>) new RestRequestBuilder<T>()
                .setUrl(baseUrl)
                .setContext(context)
                .setResponseHandler(responseHandler)
                .setBody(model)
                .build();
    }


}
