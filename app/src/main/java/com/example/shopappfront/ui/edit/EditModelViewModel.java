package com.example.shopappfront.ui.edit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopappfront.data.models.ApplicationModel;

public class EditModelViewModel<T extends ApplicationModel> extends ViewModel {

    public MutableLiveData<EditModelFormState<T>> editModelFormState;

    public EditModelViewModel() {
        this.editModelFormState = new MutableLiveData<>();
    }

    public MutableLiveData<EditModelFormState<T>> getFormState() {
        return editModelFormState;
    }


}
