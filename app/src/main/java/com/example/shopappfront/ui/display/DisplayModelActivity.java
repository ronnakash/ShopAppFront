package com.example.shopappfront.ui.display;

import android.os.Bundle;

import com.example.shopappfront.ui.activities.ShopAppActivityWithModel;
import com.example.shopappfront.data.models.ApplicationModelWithId;

public abstract class DisplayModelActivity<T extends ApplicationModelWithId>
        extends ShopAppActivityWithModel<T> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected T getModel() {
        int modelId = getIntent().getIntExtra("modelId", -1);
        return repository.getById(modelId);
    }


}
