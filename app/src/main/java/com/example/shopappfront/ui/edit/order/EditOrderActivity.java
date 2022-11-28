package com.example.shopappfront.ui.edit.order;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.shopappfront.data.OrderRepository;
import com.example.shopappfront.data.RepositoryWithId;
import com.example.shopappfront.data.adapters.OrderedItemsAdapter;
import com.example.shopappfront.data.adapters.OrderedItemsCartAdapter;
import com.example.shopappfront.data.models.Order;
import com.example.shopappfront.data.models.OrderedItems;
import com.example.shopappfront.data.requests.response.RestSingleResponse;
import com.example.shopappfront.databinding.ActivityEditOrderBinding;
import com.example.shopappfront.ui.edit.EditModelActivity;
import com.example.shopappfront.ui.edit.EditModelFormState;
import com.example.shopappfront.ui.edit.EditModelViewModel;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class EditOrderActivity extends EditModelActivity<Order> {

    ActivityEditOrderBinding binding;
    RecyclerView recyclerView;
    OrderedItemsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<OrderedItems> originalOrderedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = binding.orderOrderedItemsRecyclerViewEditOrder;
        adapter = new OrderedItemsCartAdapter(model.getOrderedItems());
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        originalOrderedItems = new ArrayList<>();
        for (OrderedItems orderedItems : model.getOrderedItems())
            originalOrderedItems.add(new OrderedItems(orderedItems.getItem(),
                    orderedItems.getQuantity(), orderedItems.getOrderId()));
    }

    @Override
    protected void setBinding() {
        binding = ActivityEditOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected RepositoryWithId<Order> getRepository() {
        return OrderRepository.getInstance();
    }

    @Override
    protected EditModelViewModel<Order> getViewModel() {
        return new ViewModelProvider(this).get(EditOrderViewModel.class);
    }

    @Override
    protected void bindModel() {
        binding.userIdTextEditOrder.setText(String.valueOf(model.getUserId()));
        binding.orderTimeEditOrder.setText(model.getTimeString());
        binding.orderTotalTextEditOrder.setText(model.getOrderTotalSum().toString());
        String status = model.getStatus();
        if (status.equals("Shipped"))
            binding.radioGroupEditOrder.check(binding.shippedRadioButtonEditOrder.getId());
        else if (status.equals("Arrived"))
            binding.radioGroupEditOrder.check(binding.arrivedRadioButtonEditOrder.getId());
        else if (status.equals("Cancelled"))
            binding.radioGroupEditOrder.check(binding.cancelledRadioButtonEditOrder.getId());
        else
            binding.radioGroupEditOrder.check(binding.pendingRadioButtonEditOrder.getId());
    }

    @Override
    protected void bindButtons() {
        cancelButton = binding.cancelButtonEditOrder;
        confirmButton = binding.saveButtonEditOrder;
        deleteButton = binding.deleteButtonEditOrder;
    }

    @Override
    protected Order getUpdatedModel() {
        int statusInt = binding.radioGroupEditOrder.getCheckedRadioButtonId();
        String status;
        if (statusInt == binding.shippedRadioButtonEditOrder.getId())
            status = "Shipped";
        else if (statusInt == binding.arrivedRadioButtonEditOrder.getId())
            status = "Arrived";
        else if (statusInt == binding.cancelledRadioButtonEditOrder.getId())
            status = "Cancelled";
        else
            status = "Pending";
        return new Order(model.getId(), model.getTime(), model.getOrderedItems(), status, model.getUserId());
    }

    @Override
    protected void setFormErrors(EditModelFormState<Order> orderEditModelFormState) {

    }

    @Override
    protected void onConfirmClick(View v) {
        Order updatedOrder = getUpdatedModel();
        if (updatedOrder.sameOrderedItemsAs(originalOrderedItems)){
            ((OrderRepository) repository).updateOrderStatus(updatedOrder, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    showError(EditOrderActivity.this, responseString);
                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    showSuccess(EditOrderActivity.this, "updated!");
                    model = new RestSingleResponse<Order>(new TypeToken<Order>() {}, responseString).getData();
                    repository.updateModel(model);
                    finish();
                }
            }, this);
        }
        else {
            repository.update(updatedOrder, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    showError(EditOrderActivity.this, responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    showSuccess(EditOrderActivity.this, "updated!");
                    Order newOrder = new RestSingleResponse<Order>(new TypeToken<Order>() {}, responseString).getData();
                    repository.addNewModel(newOrder);
                    model.setStatus("cancelled");
                    repository.updateModel(model);
                    finish();
                }
            }, this);

        }
    }

    @Override
    protected TypeToken<Order> getTypeToken() {
        return new TypeToken<>() {};
    }



}