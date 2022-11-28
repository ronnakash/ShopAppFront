package com.example.shopappfront.ui.activities;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopappfront.data.adapters.ModelAdapter;
import com.example.shopappfront.data.models.ApplicationModelWithId;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public abstract class ShopAppActivityWithModels<T extends ApplicationModelWithId>
        extends ShopAppActivityWithIDRepository<T> implements RecyclerViewInterface {

    public List<T> models;
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    public ModelAdapter<T> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        models = new ArrayList<>();
        recyclerView = getRecyclerView();
        recyclerView.setHasFixedSize(true);
        layoutManager = getLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
        adapter = getAdapter();
        recyclerView.setAdapter(adapter);
        getModels();
    }

    protected abstract ModelAdapter<T> getAdapter();

    protected abstract RecyclerView getRecyclerView();

    protected RecyclerView.LayoutManager getLayoutManager(){
        return new LinearLayoutManager(this);
    }

    protected abstract TypeToken<List<T>> getTypeToken();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repository.removeAdapter(adapter);
    }

    protected abstract void getModels();

}
