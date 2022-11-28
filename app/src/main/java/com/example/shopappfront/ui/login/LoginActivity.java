package com.example.shopappfront.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shopappfront.ui.activities.ShopAppActivity;
import com.example.shopappfront.data.AuthenticationRepository;
import com.example.shopappfront.data.models.AuthenticationModel;
import com.example.shopappfront.data.models.User;
import com.example.shopappfront.data.requests.response.RestSingleResponse;
import com.example.shopappfront.databinding.ActivityLoginBinding;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends ShopAppActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private AuthenticationRepository authenticationRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        authenticationRepository = AuthenticationRepository.getInstance();
        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;
        loginViewModel.getFormState().observe(this, loginModelEditModelFormState -> {
            LoginFormState loginFormState = (LoginFormState) loginModelEditModelFormState;
            if (loginFormState.isDataValid()) {
                loginButton.setEnabled(true);
                return;
            }
            loginButton.setEnabled(false);
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(loginFormState.getUsernameError());
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(loginFormState.getPasswordError());
            }
        });

        loginViewModel.dataChanged("", "");

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.dataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), LoginActivity.this);
            }
            return false;
        });

        loginButton.setOnClickListener(v -> {
            loadingProgressBar.setVisibility(View.VISIBLE);
            login(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(), LoginActivity.this);
        });

    }

    @Override
    protected void setBinding() {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void login(String username, String password, Context context) {
        authenticationRepository.login(username, password, context, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(context, responseString , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                AuthenticationModel ar = new RestSingleResponse<>(new TypeToken<AuthenticationModel>(){}, responseString).getData();
                User newUser = ar.getAuthUser();
                newUser.setToken(ar.getToken());
                Toast.makeText(context, "Hello " + newUser.getUsername() , Toast.LENGTH_SHORT).show();
                authenticationRepository.setLoggedInUser(newUser);
                binding.loading.setVisibility(View.INVISIBLE);
                finish();
            }
        });

    }


}