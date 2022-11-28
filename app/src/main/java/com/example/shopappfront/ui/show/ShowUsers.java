package com.example.shopappfront.ui.show;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.UserRepository;
import com.example.shopappfront.data.adapters.ModelAdapter;
import com.example.shopappfront.data.adapters.UserAdapter;
import com.example.shopappfront.data.models.User;
import com.example.shopappfront.databinding.ActivityShowUsersBinding;
import com.example.shopappfront.ui.edit.user.EditUserActivity;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ShowUsers extends ShowModelsActivity<User> {

    ActivityShowUsersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onItemClick(int pos) {
//        User user = models.get(pos);
//        Intent intent = new Intent(this, EditUserActivity.class);
//        intent.putExtra("modelId", user.getId());
//        intent.putExtra("cancelVisible", true);
//        startActivity(intent);
//
//    }

    @Override
    protected void setBinding() {
        binding = ActivityShowUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected RepositoryWithId<User> getRepository() {
        return UserRepository.getInstance();
    }

    @Override
    protected ModelAdapter<User> getAdapter() {
        return new UserAdapter(models, this, this);
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return binding.userListRecyclerViewShowUsers;
    }

    @Override
    protected TypeToken<List<User>> getTypeToken() {
        return new TypeToken<>() {};
    }

    @Override
    protected User newModel() {
        return null;
    }

    @Override
    protected Intent getEditModelIntent() {
        return new Intent(this, EditUserActivity.class);
    }

}