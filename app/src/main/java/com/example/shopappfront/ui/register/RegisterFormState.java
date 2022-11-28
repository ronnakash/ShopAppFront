package com.example.shopappfront.ui.register;

import com.example.shopappfront.data.models.User;
import com.example.shopappfront.ui.edit.EditModelFormState;

public class RegisterFormState extends EditModelFormState<User> {


    private String usernameError;
    private String userFirstNameError;
    private String userLastNameError;
    private String userPicUrlError;
    private String userNewPasswordError;
    private String userNewPasswordConfirmError;


    public RegisterFormState(String username, String userFirstName, String userLastName, String userPicUr,
                             String userNewPassword, String userNewPasswordConfirm) {
        this.usernameError = validateUsername(username);
        this.userFirstNameError = validateUserFirstName(userFirstName);
        this.userLastNameError = validateUserLastName(userLastName);
        this.userPicUrlError = validateUrl(userPicUr);
        String[] passwordErrors = validatePasswords(userNewPassword, userNewPasswordConfirm);
        this.userNewPasswordError = passwordErrors[0];
        this.userNewPasswordConfirmError = passwordErrors[1];
    }

    @Override
    public boolean isDataValid() {
        return (userFirstNameError == null && usernameError == null && userLastNameError == null &&
                userPicUrlError == null);
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getUserFirstNameError() {
        return userFirstNameError;
    }

    public String getUserLastNameError() {
        return userLastNameError;
    }

    public String getUserPicUrlError() {
        return userPicUrlError;
    }


    private static String validateUsername(String username){
        if (username == null|| username.equals(""))
            return "username cannot be empty!";
        if (!username.matches("^[a-zA-Z0-9]*$"))
            return "Username can be alphanumeric only";
        return null;
    }

    private static String validateUserFirstName(String userFirstName){
        if (userFirstName == null|| userFirstName.equals(""))
            return "First name cannot be empty!";
        if (!userFirstName.matches("^[a-zA-Z\\s-]*$"))
            return "First name can only be alphabetical";
        return null;
    }

    private static String validateUserLastName(String userLastName){
        if (userLastName == null|| userLastName.equals(""))
            return "Last name cannot be empty!";
        if (!userLastName.matches("^[a-zA-Z\\s-]*$"))
            return "Last name can only be alphabetical";
        return null;
    }

    private static String[] validatePasswords(String password, String passwordConfirm){
        String[] errors = new String[2];
        if (!password.equals(passwordConfirm))
            errors[1] = "Passwords don't match!";
        if (password.trim().length() < 6)
            errors[0] = "Password is too short!";
        return errors;
    }

    public String getUserNewPasswordError() {
        return userNewPasswordError;
    }

    public String getUserNewPasswordConfirmError() {
        return userNewPasswordConfirmError;
    }
}
