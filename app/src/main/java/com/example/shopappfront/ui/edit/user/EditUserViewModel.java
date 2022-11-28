package com.example.shopappfront.ui.edit.user;

import com.example.shopappfront.ui.register.RegisterViewModel;

public class EditUserViewModel extends RegisterViewModel {

    public EditUserViewModel() {
        super();
    }

    public void editUserFormDataChanged(String username, String userFirstName, String userLastName,
                                        String userPicUr, String userNewPassword, String userNewPasswordConfirm){
        super.editModelFormState.setValue(new EditUserFormState(username, userFirstName, userLastName,
                userPicUr, userNewPassword, userNewPasswordConfirm));

    }


}
