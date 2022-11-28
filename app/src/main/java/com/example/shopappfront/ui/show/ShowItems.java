package com.example.shopappfront.ui.show;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;

import com.example.shopappfront.data.ItemRepository;
import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.adapters.ItemAdapter;
import com.example.shopappfront.data.adapters.ModelAdapter;
import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.databinding.ActivityShowItemsBinding;
import com.example.shopappfront.ui.edit.item.EditItemActivity;
import com.google.gson.reflect.TypeToken;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ShowItems extends ShowModelsActivity<Item> {

    ActivityShowItemsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setBinding() {
        binding = ActivityShowItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        newModelButton = binding.newItemButtonShowItems;
    }

    @Override
    protected RepositoryWithId<Item> getRepository() {
        return ItemRepository.getInstance();
    }

    @Override
    protected ModelAdapter<Item> getAdapter() {
        return new ItemAdapter(models, this);
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return binding.itemsListRecyclerView;
    }

    @Override
    protected TypeToken<List<Item>> getTypeToken() {
        return new TypeToken<List<Item>>(){};
    }

    @Override
    protected Item newModel() {
        return new Item("", "", new BigDecimal("99.99"), 0,
                null, new ArrayList(), "", -1);
    }

    @Override
    protected Intent getEditModelIntent() {
        return new Intent(this, EditItemActivity.class);
    }
}
