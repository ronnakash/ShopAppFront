package com.example.shopappfront.ui.edit.item;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;

import com.example.shopappfront.data.CategoryRepository;
import com.example.shopappfront.data.ItemRepository;
import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.adapters.CategoryItemAdapter;
import com.example.shopappfront.data.models.Category;
import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.data.requests.response.RestManyResponse;
import com.example.shopappfront.data.requests.response.TextHttpResponseHandlerWithDefault;
import com.example.shopappfront.databinding.ActivityEditItemBinding;
import com.example.shopappfront.ui.edit.EditModelFormState;
import com.example.shopappfront.ui.edit.EditModelViewModel;
import com.example.shopappfront.ui.edit.EditModelWithTextWatcherActivity;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class EditItemActivity extends EditModelWithTextWatcherActivity<Item> {

    ActivityEditItemBinding binding;
    RecyclerView recyclerView;
    List<Category> categories;
    CategoryItemAdapter adapter;
    CategoryRepository categoryRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryRepository = CategoryRepository.getInstance();
        recyclerView = binding.itemCategoriesRecyclerViewEditItem;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categories = new ArrayList<>();
        adapter = new CategoryItemAdapter(categories, model.getCategoriesList());
        recyclerView.setAdapter(adapter);
        categoryRepository.getAll(new TextHttpResponseHandlerWithDefault<List<Category>>() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showError(EditItemActivity.this, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                categories = new RestManyResponse<Category>(new TypeToken<>(){}, responseString).getData();
                categoryRepository.setModels(categories);
                adapter.setModels(categories);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void defaultResponse(List<Category> obj) {
                categories = obj;
                adapter.setModels(categories);
                adapter.notifyDataSetChanged();
            }
        }, this);

    }

    @Override
    protected void setBinding() {
        binding = ActivityEditItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void bindModel() {
        binding.itemNameEditItem.setText(model.getItemName());
        binding.itemDescriptionEditItem.setText(model.getItemDescription());
        binding.itemBasePriceEditItem.setText(String.valueOf(model.getItemBasePrice()));
        binding.itemDiscountEditItem.setText(String.valueOf(model.getItemDiscount()));
        if (model.getPicUrl() != null)
            binding.itemPicurlEditItem.setText(model.getPicUrl());
    }

    @Override
    protected void bindButtons() {
        cancelButton = binding.cancelButtonEditItems;
        confirmButton = binding.saveButtonEditItems;
        deleteButton = binding.deleteButtonEditItems;
    }


    @Override
    protected RepositoryWithId<Item> getRepository() {
        return ItemRepository.getInstance();
    }

    @Override
    protected EditModelViewModel<Item> getViewModel() {
        return new ViewModelProvider(this).get(EditItemViewModel.class);
    }

    @Override
    protected Item getUpdatedModel() {
        List<Category> updatedCategories = adapter.getMarked();
        return new Item(binding.itemNameEditItem.getText().toString(),
                binding.itemDescriptionEditItem.getText().toString(),
                new BigDecimal(binding.itemBasePriceEditItem.getText().toString()).setScale(2),
                Integer.valueOf(binding.itemDiscountEditItem.getText().toString()),
                updatedCategories, binding.itemPicurlEditItem.getText().toString(), model.getId());
    }

    @Override
    protected void setFormErrors(EditModelFormState<Item> itemEditModelFormState) {
        EditItemFormState formState = (EditItemFormState) itemEditModelFormState;
        if ( formState==null || formState.isDataValid())
            return;
        if (formState.getItemNameError()!=null)
            binding.itemNameEditItem.setError(formState.getItemNameError());
        if (formState.getItemDescriptionError()!=null)
            binding.itemDescriptionEditItem.setError(formState.getItemDescriptionError());
        if (formState.getItemDiscountError()!=null)
            binding.itemDiscountEditItem.setError(formState.getItemDiscountError());
        if (formState.getItemBasePriceError()!=null)
            binding.itemBasePriceEditItem.setError(formState.getItemBasePriceError());
        if (formState.getItemPicUrlError()!=null)
            binding.itemPicurlEditItem.setError(formState.getItemPicUrlError());
    }

    @Override
    protected TypeToken<Item> getTypeToken() {
        return new TypeToken<>() {};
    }

    @Override
    public void afterTextChanged(Editable s) {
        ( (EditItemViewModel) viewModel).dataChanged(
                binding.itemNameEditItem.getText().toString(),
                binding.itemDescriptionEditItem.getText().toString(),
                binding.itemBasePriceEditItem.getText().toString(),
                binding.itemDiscountEditItem.getText().toString(),
                binding.itemPicurlEditItem.getText().toString());
    }

    @Override
    protected void addTextWatcher() {
        binding.itemNameEditItem.addTextChangedListener(this);
        binding.itemDescriptionEditItem.addTextChangedListener(this);
        binding.itemBasePriceEditItem.addTextChangedListener(this);
        binding.itemDiscountEditItem.addTextChangedListener(this);
        binding.itemPicurlEditItem.addTextChangedListener(this);
    }
}