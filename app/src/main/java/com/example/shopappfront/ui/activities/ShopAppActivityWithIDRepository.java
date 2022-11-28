package com.example.shopappfront.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.models.ApplicationModelWithId;

public abstract class ShopAppActivityWithIDRepository<T extends ApplicationModelWithId>
        extends ShopAppActivity {

    public RepositoryWithId<T> repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = getRepository();
    }

    protected abstract RepositoryWithId<T> getRepository();

}
