package com.example.shopappfront.ui.display;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopappfront.data.OrderRepository;
import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.adapters.OrderedItemsListAdapter;
import com.example.shopappfront.data.models.Order;
import com.example.shopappfront.data.requests.response.RestSingleResponse;
import com.example.shopappfront.databinding.ActivityDisplayOrderBinding;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cz.msebera.android.httpclient.Header;

public class DisplayOrderActivity extends DisplayModelActivity<Order>
        implements View.OnClickListener {

    ActivityDisplayOrderBinding binding;
    OrderedItemsListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new OrderedItemsListAdapter(model.getOrderedItems());
        binding.orderOrderedItemsListOrderList.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        binding.orderOrderedItemsListOrderList.setLayoutManager(layoutManager);
        cancelButton = binding.orderCancelOrderButtonOrderList;
        cancelButton.setVisibility(View.GONE);
        cancelButton.setOnClickListener(this);
    }

    @Override
    protected Order getModel() {
        int modelId = getIntent().getIntExtra("modelId", -1);
        return ((OrderRepository) repository).getUsersOrderById(modelId);
    }

    @Override
    protected void bindModel() {
        binding.orderIdOrderListText.setText(String.valueOf(model.getId()));
        binding.orderDateOrderListText.setText(model.getTimeString());
        binding.orderStatusOrderListText.setText(model.getStatus());
        binding.orderTotalAmountOrderListText.setText(model.getOrderTotalSum().toString());
    }

    @Override
    protected void setBinding() {
        binding = ActivityDisplayOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected RepositoryWithId<Order> getRepository() {
        return OrderRepository.getInstance();
    }


    @Override
    public void onClick(View v) {
        model.setStatus("Cancelled");
        ((OrderRepository) repository).updateOrderStatus(model, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showError(DisplayOrderActivity.this, responseString);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                showSuccess(DisplayOrderActivity.this, "Order cancelled!");
                model = new RestSingleResponse<Order>(new TypeToken<Order>() {}, responseString).getData();
                repository.updateModel(model);
                finish();
            }
        }, this);
    }
}