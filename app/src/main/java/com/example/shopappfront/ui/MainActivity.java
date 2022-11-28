package com.example.shopappfront.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shopappfront.data.AuthenticationRepository;
import com.example.shopappfront.data.Repository;
import com.example.shopappfront.data.models.AuthenticationApplicationModel;
import com.example.shopappfront.data.models.User;
import com.example.shopappfront.databinding.ActivityMainBinding;
import com.example.shopappfront.ui.activities.ShopAppActivityWithRepository;
import com.example.shopappfront.ui.display.DisplayUserActivity;
import com.example.shopappfront.ui.login.LoginActivity;
import com.example.shopappfront.ui.register.RegisterActivity;
import com.example.shopappfront.ui.show.BrowseItemsActivity;

public class MainActivity extends ShopAppActivityWithRepository<AuthenticationApplicationModel> {

    ActivityMainBinding binding;
    Button registerButton;
    Button loginButton;
    Button profileButton;
    Button browseButton;
    Button adminButton;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerButton = binding.registerButtonHome;
        loginButton = binding.loginButtonHome;
        profileButton = binding.profileButtonHome;
        browseButton = binding.storeButtonHome;
        adminButton = binding.adminButtonHome;
        logoutButton = binding.logoutButtonHome;
        registerButton.setOnClickListener(this::pressRegisterButton);
        loginButton.setOnClickListener(this::pressLoginButton);
        profileButton.setOnClickListener(this::pressProfileButton);
        browseButton.setOnClickListener(this::pressBrowseButton);
        adminButton.setOnClickListener(this::pressAdminButton);
        logoutButton.setOnClickListener(this::pressLogoutButton);
    }

    @Override
    protected Repository<AuthenticationApplicationModel> getRepository() {
        return AuthenticationRepository.getInstance();
    }

    @Override
    protected void setBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void pressRegisterButton(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void pressLoginButton(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void pressProfileButton(View v) {
        Intent intent = new Intent(this, DisplayUserActivity.class);
        startActivity(intent);
    }

    private void pressBrowseButton(View v) {
        Intent intent = new Intent(this, BrowseItemsActivity.class);
        startActivity(intent);
    }

    private void pressAdminButton(View v) {
        Intent intent = new Intent(this, AdminScreenActivity.class);
        startActivity(intent);
    }

    private void pressLogoutButton(View v) {
        ((AuthenticationRepository) repository).logout();
        setButtonsByUser(null);
    }

    public void setButtonsByUser(User user){
        int permissions = 0;
        if (user != null){
            if (user.getPermissions() == null || user.getPermissions().equals("User"))
                permissions = 1;
            else
                permissions = 2;
        }
        registerButton.setVisibility(permissions==0? View.VISIBLE : View.GONE);
        loginButton.setVisibility(permissions==0? View.VISIBLE : View.GONE);
        profileButton.setVisibility(permissions!=0? View.VISIBLE : View.GONE);
        adminButton.setVisibility(permissions==2? View.VISIBLE : View.GONE);
        logoutButton.setVisibility(permissions!=0? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setButtonsByUser(((AuthenticationRepository) repository).getLoggedInUser());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}