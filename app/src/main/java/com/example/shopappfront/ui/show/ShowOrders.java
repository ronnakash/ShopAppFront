package com.example.shopappfront.ui.show;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shopappfront.databinding.ActivityShowOrdersBinding;


public class ShowOrders extends ShowOrdersBaseActivity {

    ActivityShowOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setBinding() {
        binding = ActivityShowOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return binding.orderesListRecyclerView;
    }

}