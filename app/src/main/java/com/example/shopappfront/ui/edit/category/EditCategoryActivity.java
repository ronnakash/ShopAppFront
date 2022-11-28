package com.example.shopappfront.ui.edit.category;

import android.os.Bundle;
import android.text.Editable;

import com.example.shopappfront.data.CategoryRepository;
import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.models.Category;
import com.example.shopappfront.databinding.ActivityEditCategoryBinding;
import com.example.shopappfront.ui.edit.EditModelFormState;
import com.example.shopappfront.ui.edit.EditModelViewModel;
import com.example.shopappfront.ui.edit.EditModelWithTextWatcherActivity;
import com.google.gson.reflect.TypeToken;

public class EditCategoryActivity extends EditModelWithTextWatcherActivity<Category> {

    ActivityEditCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setBinding() {
        binding = ActivityEditCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected RepositoryWithId<Category> getRepository() {
        return CategoryRepository.getInstance();
    }

    @Override
    protected EditModelViewModel<Category> getViewModel() {
        return new EditCategoryViewModel();
    }

    @Override
    protected void bindModel() {
        binding.categoryNameEditCategory.setText(model.getCategoryName());
    }

    @Override
    protected void bindButtons() {
        cancelButton = binding.cancelButtonEditItems;
        confirmButton = binding.saveButtonEditItems;
        deleteButton = binding.deleteButtonEditItems;
    }

    @Override
    protected Category getUpdatedModel() {
        return new Category(model.getId(), binding.categoryNameEditCategory.getText().toString());
    }

    @Override
    protected void setFormErrors(EditModelFormState<Category> categoryEditModelFormState) {
        EditCategoryFormState formState = (EditCategoryFormState) categoryEditModelFormState;
        if (formState == null || formState.isDataValid())
            return;
        binding.categoryNameEditCategory.setError(formState.categoryNameError);
    }

    @Override
    protected TypeToken<Category> getTypeToken() {
        return new TypeToken<>() {};
    }

    @Override
    public void afterTextChanged(Editable s) {
        ( (EditCategoryViewModel) viewModel).dataChanged(
                binding.categoryNameEditCategory.getText().toString());
    }

    @Override
    protected void addTextWatcher() {
        binding.categoryNameEditCategory.addTextChangedListener(this);
    }
}