package com.example.shopappfront.ui.show;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shopappfront.data.CategoryRepository;
import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.adapters.CategoryListAdapter;
import com.example.shopappfront.data.adapters.ModelAdapter;
import com.example.shopappfront.data.models.Category;
import com.example.shopappfront.databinding.ActivityShowCategoriesBinding;
import com.example.shopappfront.ui.edit.category.EditCategoryActivity;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ShowCategories extends ShowModelsActivity<Category> {

    ActivityShowCategoriesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setBinding() {
        binding= ActivityShowCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        newModelButton = binding.newCategoryButtonShowItems;
    }

    @Override
    protected RepositoryWithId<Category> getRepository() {
        return CategoryRepository.getInstance();
    }

    @Override
    protected ModelAdapter<Category> getAdapter() {
        return new CategoryListAdapter(models,this);
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return binding.categoryRecyclerViewShowCategories;
    }

    @Override
    protected TypeToken<List<Category>> getTypeToken() {
        return new TypeToken<>() {};
    }

    @Override
    protected Category newModel() {
        return new Category(-1, "");
    }

    @Override
    protected Intent getEditModelIntent() {
        return new Intent(this, EditCategoryActivity.class);
    }
}