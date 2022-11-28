package com.example.shopappfront.ui.edit;

import android.os.Bundle;
import android.text.TextWatcher;

import com.example.shopappfront.data.models.ApplicationModelWithId;

public abstract class EditModelWithTextWatcherActivity<T extends ApplicationModelWithId>
        extends EditModelActivity<T> implements TextWatcher {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addTextWatcher();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    protected abstract void addTextWatcher();

}
