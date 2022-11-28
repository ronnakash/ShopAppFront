package com.example.shopappfront.ui.display;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shopappfront.ui.ShoppingCartActivity;
import com.example.shopappfront.data.ItemRepository;
import com.example.shopappfront.data.AuthenticationRepository;
import com.example.shopappfront.data.OrderRepository;
import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.databinding.ActivityDisplayItemBinding;
import com.example.shopappfront.ui.login.LoginActivity;
import com.squareup.picasso.Picasso;

public class DisplayItemActivity extends DisplayModelActivity<Item> implements View.OnClickListener {

    ActivityDisplayItemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.addToCartButtonDisplayItem.setOnClickListener(this);
        binding.shoppingCartButtonDisplayItem.setOnClickListener(v -> {
            if (!AuthenticationRepository.getInstance().isLoggedIn()){
                Intent intent = new Intent(DisplayItemActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(DisplayItemActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }

    @Override
    protected void bindModel(){
        if (model.getPicUrl() != null)
            Picasso.get().load(model.getPicUrl()).resize(400,400).into(binding.itemImageItemDisplay);
        binding.itemDescriptionDisplayItem.setText(model.getItemDescription());
        binding.itemNameItemDisplay.setText(model.getItemName());
        binding.itemFinalPriceDisplayItem.setText(String.valueOf(model.getItemFinalPrice()));
        binding.itemBasePriceDisplayItem.setText(String.valueOf(model.getItemBasePrice()));
    }


    @Override
    protected void setBinding() {
        binding = ActivityDisplayItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onClick(View v) {
        if (!AuthenticationRepository.getInstance().isLoggedIn()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else
            OrderRepository.getInstance().addItemsToCartOrder(model);
    }

    @Override
    protected RepositoryWithId<Item> getRepository() {
        return ItemRepository.getInstance();
    }


}