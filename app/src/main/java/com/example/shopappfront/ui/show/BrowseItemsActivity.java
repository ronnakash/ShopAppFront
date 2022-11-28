package com.example.shopappfront.ui.show;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shopappfront.ui.display.DisplayItemActivity;
import com.example.shopappfront.ui.ShoppingCartActivity;
import com.example.shopappfront.data.adapters.ItemAdapter;
import com.example.shopappfront.data.adapters.ModelAdapter;
import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.databinding.ActivityBrowseItemsBinding;

public class BrowseItemsActivity extends ShowItems implements View.OnClickListener{

    ActivityBrowseItemsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.shoppingCartFloatButtonBrowseItems.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int pos) {
        Item model = super.models.get(pos);
        Intent intent = new Intent(this, DisplayItemActivity.class);
        intent.putExtra("modelId", model.getId());
        startActivity(intent);
    }

    @Override
    protected void setBinding() {
        binding = ActivityBrowseItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        newModelButton = null;
    }

    @Override
    protected ModelAdapter<Item> getAdapter() {
        return new ItemAdapter(models, this);
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return binding.itemsListRecyclerView;
    }

}