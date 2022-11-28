package com.example.shopappfront.ui.show;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shopappfront.ui.display.DisplayOrderActivity;
import com.example.shopappfront.data.OrderRepository;
import com.example.shopappfront.data.models.Order;
import com.example.shopappfront.data.requests.response.RestManyResponse;
import com.example.shopappfront.data.requests.response.TextHttpResponseHandlerWithDefault;
import com.example.shopappfront.databinding.ActivityShowMyOrdersBinding;

import java.util.List;

import cz.msebera.android.httpclient.Header;


public class ShowMyOrdersActivity extends ShowOrdersBaseActivity {

    ActivityShowMyOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setBinding() {
        binding = ActivityShowMyOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    protected RecyclerView getRecyclerView() {
        return binding.myOrdersRecyclerView;
    }

    @Override
    protected void getModels() {
        ((OrderRepository) repository).getUsersOrders(new TextHttpResponseHandlerWithDefault<List<Order>>() {
            @Override
            public void defaultResponse(List<Order> obj) {
                models = obj;
                adapter.setModels(models);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showError(ShowMyOrdersActivity.this, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                models = new RestManyResponse<>(getTypeToken(), responseString).getData();
                adapter.setModels(models);
                ((OrderRepository) repository).setUsersOrders(models);
                adapter.notifyDataSetChanged();
            }
        }, this);
    }

    @Override
    public void onItemClick(int pos) {
        Order model = models.get(pos);
        Intent intent = new Intent(this, DisplayOrderActivity.class);
        intent.putExtra("modelId", model.getId());
        intent.putExtra("cancelVisible", true);
        startActivity(intent);
    }



}