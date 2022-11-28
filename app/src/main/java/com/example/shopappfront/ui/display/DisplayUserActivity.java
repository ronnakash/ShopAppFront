package com.example.shopappfront.ui.display;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shopappfront.data.AuthenticationRepository;
import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.UserRepository;
import com.example.shopappfront.data.models.User;
import com.example.shopappfront.databinding.ActivityDisplayUserBinding;
import com.example.shopappfront.ui.edit.user.EditUserActivity;
import com.example.shopappfront.ui.show.ShowMyOrdersActivity;
import com.squareup.picasso.Picasso;

public class DisplayUserActivity extends DisplayModelActivity<User> {

    ActivityDisplayUserBinding binding;
    Button myOrdersButton;
    Button editUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myOrdersButton.setOnClickListener(this::onMyOrdersButtonClick);
        editUserButton.setOnClickListener(this::onEditUserButtonClick);
    }

    @Override
    protected void bindModel() {
        if (model.getPicUrl() != null)
            Picasso.get().load(model.getPicUrl()).resize(400,400).into(binding.userImageUserDisplay);
        binding.usernameUserDisplay.setText(model.getUsername());
        binding.userFirstNameDisplayUser.setText(model.getFirstName());
        binding.userLastNameDisplayUser.setText(model.getLastName());
    }

    @Override
    protected void setBinding() {
        binding = ActivityDisplayUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myOrdersButton = binding.showMyOrdersButtonDisplayUser;
        editUserButton = binding.editUserButtonDisplayUser;
    }

    @Override
    protected User getModel() {
        return AuthenticationRepository.getInstance().getLoggedInUser();
    }

    private void onMyOrdersButtonClick(View v){
        Intent intent = new Intent(this, ShowMyOrdersActivity.class);
        startActivity(intent);
    }

    private void onEditUserButtonClick(View v){
        Intent intent = new Intent(this, EditUserActivity.class);
        intent.putExtra("modelId", model.getId());
        startActivity(intent);
        finish();
    }

    @Override
    protected RepositoryWithId<User> getRepository() {
        return UserRepository.getInstance();
    }
}