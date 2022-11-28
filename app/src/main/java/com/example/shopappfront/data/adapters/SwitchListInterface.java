package com.example.shopappfront.data.adapters;

import com.example.shopappfront.data.models.ApplicationModelWithId;

public interface SwitchListInterface<T extends ApplicationModelWithId> {

    void onSwitch(boolean isChecked, T model);

}
