package com.example.shopappfront.ui.login;

import com.example.shopappfront.data.models.LoginModel;
import com.example.shopappfront.ui.edit.EditModelFormState;


class LoginFormState extends EditModelFormState<LoginModel> {

    private String usernameError;
    private String passwordError;

    LoginFormState(String username, String password) {
        this.usernameError = validateUsername(username);
        this.passwordError = validatePassword(password);
    }

    private static String validateUsername(String username){
        if (username == null|| username.equals(""))
            return "username cannot be empty!";
        if (!username.matches("^[a-zA-Z0-9]*$"))
            return "Username can be alphanumeric only";
        return null;
    }

    private static String validatePassword(String password){
        if (password.trim().length() < 6)
            return "Password is too short!";
        return null;
    }

    String getUsernameError() {
        return usernameError;
    }


    String getPasswordError() {
        return passwordError;
    }

    @Override
    public boolean isDataValid() {
        return usernameError == null && passwordError == null;
    }
}