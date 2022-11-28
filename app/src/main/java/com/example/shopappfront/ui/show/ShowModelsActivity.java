package com.example.shopappfront.ui.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shopappfront.ui.activities.ShopAppActivityWithModels;
import com.example.shopappfront.data.models.ApplicationModelWithId;
import com.example.shopappfront.data.requests.response.RestManyResponse;
import com.example.shopappfront.data.requests.response.TextHttpResponseHandlerWithDefault;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public abstract class ShowModelsActivity<T extends ApplicationModelWithId>
        extends ShopAppActivityWithModels<T> {

    FloatingActionButton newModelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository.addAdapter(adapter);
        if (newModelButton != null){
            newModelButton.setOnClickListener(this::startNewModelActivity);
        }
    }

    //empty model for create model activity
    protected abstract T newModel();

    protected abstract Intent getEditModelIntent();

    @Override
    protected void getModels() {
        repository.getAll(new TextHttpResponseHandlerWithDefault<List<T>>() {
            @Override
            public void defaultResponse(List<T> obj) {
                models = obj;
                adapter.setModels(models);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showError(ShowModelsActivity.this, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                models = new RestManyResponse<T>(getTypeToken(), responseString).getData();
                repository.setModels(models);
            }
        }, this);
    }


    @Override
    public void onItemClick(int pos) {
        T model = models.get(pos);
        Intent intent = getEditModelIntent();
        intent.putExtra("modelId", model.getId());
        intent.putExtra("cancelVisible", true);
        startActivity(intent);
    }

    private void startNewModelActivity(View v){
        T model = newModel();
        repository.addNewModel(model);
        Intent intent = getEditModelIntent();
        intent.putExtra("modelId", model.getId());
        intent.putExtra("cancelVisible", false);
        startActivity(intent);
    }

}
