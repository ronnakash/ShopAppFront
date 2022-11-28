package com.example.shopappfront.ui.display;

import com.example.shopappfront.data.OrderRepository;
import com.example.shopappfront.data.models.Order;
import com.example.shopappfront.data.requests.response.RestSingleResponse;
import com.google.gson.reflect.TypeToken;

public class DisplayNewOrderActivity extends DisplayOrderActivity{

    @Override
    protected Order getModel() {
        String orderResponseString = getIntent().getStringExtra("newOrderResponse");
        Order order = new RestSingleResponse<Order>(new TypeToken<>() {}, orderResponseString).getData();
        OrderRepository.getInstance().addNewModel(order);
        return order;
    }
}
