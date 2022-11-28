package com.example.shopappfront.ui.show;

import android.content.Intent;

import com.example.shopappfront.data.OrderRepository;
import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.adapters.ModelAdapter;
import com.example.shopappfront.data.adapters.OrderAdapter;
import com.example.shopappfront.data.models.Order;
import com.example.shopappfront.ui.edit.order.EditOrderActivity;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public abstract class ShowOrdersBaseActivity extends ShowModelsActivity<Order>{

    @Override
    protected RepositoryWithId<Order> getRepository() {
        return OrderRepository.getInstance();
    }

    @Override
    protected ModelAdapter<Order> getAdapter() {
        return new OrderAdapter(models, this, this);
    }


    @Override
    protected TypeToken<List<Order>> getTypeToken() {
        return new TypeToken<List<Order>>() {};
    }

    @Override
    protected Order newModel() {
        return null;
    }

    @Override
    protected Intent getEditModelIntent() {
        return new Intent(this, EditOrderActivity.class);
    }

}
