package com.example.shopappfront.ui.register;

import com.example.shopappfront.data.models.User;
import com.example.shopappfront.ui.edit.EditModelViewModel;

public class RegisterViewModel extends EditModelViewModel<User> {

    public RegisterViewModel() {
        super();
        super.editModelFormState.setValue(new RegisterFormState("", "", "",
                "", "", ""));
    }

    public void registerFormDataChanged(String username, String userFirstName, String userLastName,
            String userPicUr, String userNewPassword, String userNewPasswordConfirm){
        super.editModelFormState.setValue(new RegisterFormState(username, userFirstName, userLastName,
                userPicUr, userNewPassword, userNewPasswordConfirm));
    }

}
