package com.example.shopappfront.ui.login;

import androidx.lifecycle.MutableLiveData;

import com.example.shopappfront.data.models.LoginModel;
import com.example.shopappfront.ui.edit.EditModelViewModel;

public class LoginViewModel extends EditModelViewModel<LoginModel> {

    private MutableLiveData<LoginFormState> loginFormState;

    public LoginViewModel(){
        super();
    }



    public void dataChanged(String username, String password) {
        super.editModelFormState.setValue(new LoginFormState(username, password));
    }


}