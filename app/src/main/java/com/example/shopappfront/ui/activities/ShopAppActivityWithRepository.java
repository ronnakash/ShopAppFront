package com.example.shopappfront.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.shopappfront.data.Repository;
import com.example.shopappfront.data.models.ApplicationModel;

public abstract class ShopAppActivityWithRepository<T extends ApplicationModel>
        extends ShopAppActivity {

    public Repository<T> repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = getRepository();
    }

    protected abstract Repository<T> getRepository();

}
