package com.example.shopappfront.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopappfront.data.AuthenticationRepository;
import com.example.shopappfront.data.models.AuthenticationModel;
import com.example.shopappfront.data.models.User;
import com.example.shopappfront.data.requests.response.RestSingleResponse;
import com.example.shopappfront.databinding.ActivityRegisterBinding;
import com.example.shopappfront.ui.login.LoginActivity;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;
    private AuthenticationRepository authenticationRepository;
    private EditText usernameEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText picUrlEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;
    private Button registerButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        authenticationRepository = AuthenticationRepository.getInstance();
        usernameEditText = binding.registerUsernameRegister;
        firstNameEditText = binding.registerFirstNameRegister;
        lastNameEditText = binding.registerLastNameRegister;
        picUrlEditText = binding.registerPicurlRegister;
        passwordEditText = binding.registerPasswordRegister;
        passwordConfirmEditText = binding.registerConfirmPasswordRegister;
        registerButton = binding.registerButtonRegister;
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.registerFormDataChanged(
                        usernameEditText.getText().toString(),
                        firstNameEditText.getText().toString(),
                        lastNameEditText.getText().toString(),
                        picUrlEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        passwordConfirmEditText.getText().toString()
                );
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        firstNameEditText.addTextChangedListener(afterTextChangedListener);
        lastNameEditText.addTextChangedListener(afterTextChangedListener);
        picUrlEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordConfirmEditText.addTextChangedListener(afterTextChangedListener);

        viewModel.getFormState().observe(this,registerFormState -> {
            setFormErrors((RegisterFormState) registerFormState);
            registerButton.setEnabled(viewModel.getFormState().getValue().isDataValid());
        });

        registerButton.setOnClickListener(v -> {
            authenticationRepository.register(new User(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    firstNameEditText.getText().toString(),
                    lastNameEditText.getText().toString(),
                    picUrlEditText.getText().toString()), this, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Toast.makeText(RegisterActivity.this, responseString , Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    AuthenticationModel authentication = new RestSingleResponse<>(new TypeToken<AuthenticationModel>(){}, responseString).getData();
                    User newUser = authentication.getAuthUser();
                    Toast.makeText(RegisterActivity.this, "Registered " + newUser.getUsername() +"!" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        });

    }

    private void setFormErrors(RegisterFormState formState){
        if (formState == null || formState.isDataValid())
            return;
        if(formState.getUsernameError()!=null)
            usernameEditText.setError(formState.getUsernameError());
        if(formState.getUserFirstNameError()!=null)
            firstNameEditText.setError(formState.getUserFirstNameError());
        if(formState.getUserLastNameError()!=null)
            lastNameEditText.setError(formState.getUserLastNameError());
        if(formState.getUserPicUrlError()!=null)
            picUrlEditText.setError(formState.getUserPicUrlError());
        if(formState.getUserNewPasswordError()!=null)
            passwordEditText.setError(formState.getUserNewPasswordError());
        if(formState.getUserNewPasswordError()!=null)
            passwordConfirmEditText.setError(formState.getUserNewPasswordError());

    }

}