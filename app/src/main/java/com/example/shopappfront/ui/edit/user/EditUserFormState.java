package com.example.shopappfront.ui.edit.user;

import com.example.shopappfront.ui.register.RegisterFormState;

public class EditUserFormState extends RegisterFormState {


    public EditUserFormState(String username, String userFirstName, String userLastName, String userPicUr,
                             String userNewPassword, String userNewPasswordConfirm) {
        super(username, userFirstName, userLastName, userPicUr, userNewPassword, userNewPasswordConfirm);

    }

    @Override
    public boolean isDataValid() {
        return (super.isDataValid());
    }


}
