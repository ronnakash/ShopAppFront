package com.example.shopappfront.ui.edit.user;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.example.shopappfront.data.AuthenticationRepository;
import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.UserRepository;
import com.example.shopappfront.data.models.User;
import com.example.shopappfront.databinding.ActivityEditUserBinding;
import com.example.shopappfront.ui.edit.EditModelFormState;
import com.example.shopappfront.ui.edit.EditModelViewModel;
import com.example.shopappfront.ui.edit.EditModelWithTextWatcherActivity;
import com.google.gson.reflect.TypeToken;

public class EditUserActivity extends EditModelWithTextWatcherActivity<User> {

    ActivityEditUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!AuthenticationRepository.getInstance().getLoggedInUser().equals(model)){
//            binding.userOldPasswordEditUser.setVisibility(View.GONE);
//            binding.userNewPasswordEditUser.setVisibility(View.GONE);
//            binding.userNewPasswordConfirmEditUser.setVisibility(View.GONE);
//        }
        binding.userOldPasswordEditUser.setVisibility(View.GONE);
        binding.userNewPasswordEditUser.setVisibility(View.GONE);
        binding.userNewPasswordConfirmEditUser.setVisibility(View.GONE);
    }

    @Override
    protected void setBinding() {
        binding = ActivityEditUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected RepositoryWithId<User> getRepository() {
        return UserRepository.getInstance();
    }

    @Override
    protected EditModelViewModel<User> getViewModel() {
        return new EditUserViewModel();
    }

    @Override
    protected void bindModel() {
        binding.userFirstNameEditUser.setText(model.getFirstName());
        binding.userUsernameEditUser.setText(model.getUsername());
        binding.userLastNameEditUser.setText(model.getLastName());
        binding.userPicUrlEditUser.setText(model.getPicUrl());
    }

    @Override
    protected void bindButtons() {
        cancelButton = binding.cancelButtonEditUsers;
        confirmButton = binding.saveButtonEditUsers;
        deleteButton = binding.deleteButtonEditUsers;
    }

    @Override
    protected User getUpdatedModel() {
        return new User(model.getId(), binding.userUsernameEditUser.getText().toString(),
                binding.userFirstNameEditUser.getText().toString(),
                binding.userLastNameEditUser.getText().toString(),
                model.getToken(), binding.userPicUrlEditUser.getText().toString());
    }

    @Override
    protected void setFormErrors(EditModelFormState<User> editModelFormState) {
        EditUserFormState formState = (EditUserFormState) editModelFormState;
        if ( formState==null || formState.isDataValid())
            return;
        if(formState.getUsernameError()!=null)
            binding.userUsernameEditUser.setError(formState.getUsernameError());
        if(formState.getUserFirstNameError()!=null)
            binding.userFirstNameEditUser.setError(formState.getUserFirstNameError());
        if(formState.getUserLastNameError()!=null)
            binding.userLastNameEditUser.setError(formState.getUserLastNameError());
        if(formState.getUserPicUrlError()!=null)
            binding.userPicUrlEditUser.setError(formState.getUserPicUrlError());
        if(formState.getUserNewPasswordError()!=null)
            binding.userNewPasswordEditUser.setError(formState.getUserNewPasswordError());
        if(formState.getUserNewPasswordConfirmError()!=null)
            binding.userNewPasswordConfirmEditUser.setError(formState.getUserNewPasswordConfirmError());
    }

    @Override
    protected void addTextWatcher() {
        binding.userUsernameEditUser.addTextChangedListener(this);
        binding.userFirstNameEditUser.addTextChangedListener(this);
        binding.userLastNameEditUser.addTextChangedListener(this);
        binding.userPicUrlEditUser.addTextChangedListener(this);
        binding.userNewPasswordConfirmEditUser.addTextChangedListener(this);
        binding.userNewPasswordConfirmEditUser.addTextChangedListener(this);
    }

    @Override
    protected void onStart() {
        if (model.getId() == AuthenticationRepository.getInstance().getUserId())
            deleteButton.setVisibility(View.GONE);
        super.onStart();
    }

    @Override
    protected TypeToken<User> getTypeToken() {
        return new TypeToken<>() {};
    }


    @Override
    public void afterTextChanged(Editable s) {
        ( (EditUserViewModel) viewModel).editUserFormDataChanged(
                binding.userUsernameEditUser.getText().toString(),
                binding.userFirstNameEditUser.getText().toString(),
                binding.userLastNameEditUser.getText().toString(),
                binding.userPicUrlEditUser.getText().toString(),
                binding.userNewPasswordConfirmEditUser.getText().toString(),
                binding.userNewPasswordConfirmEditUser.getText().toString());
    }




}