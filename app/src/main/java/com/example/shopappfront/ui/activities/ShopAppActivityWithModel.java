package com.example.shopappfront.ui.activities;

import android.os.Bundle;

import com.example.shopappfront.data.models.ApplicationModelWithId;

public abstract class ShopAppActivityWithModel<T extends ApplicationModelWithId> extends ShopAppActivityWithIDRepository<T> {

    public T model;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = getModel();
    }

    protected abstract T getModel();

    protected abstract void bindModel();

    @Override
    protected void onStart() {
        super.onStart();
        if (model == null)
            model=getModel();
        bindModel();
    }

}
