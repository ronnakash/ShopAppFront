package com.example.shopappfront.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopappfront.data.OrderRepository;
import com.example.shopappfront.data.adapters.OrderedItemsCartAdapter;
import com.example.shopappfront.data.models.Order;
import com.example.shopappfront.data.requests.response.RestSingleResponse;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;
import com.example.shopappfront.databinding.ActivityShoppingCartBinding;
import com.example.shopappfront.ui.display.DisplayNewOrderActivity;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class ShoppingCartActivity extends AppCompatActivity implements RecyclerViewInterface, View.OnClickListener {

    Order shoppingCartOrder;
    ActivityShoppingCartBinding binding;
    OrderRepository orderRepository;
    RecyclerView recyclerView;
    OrderedItemsCartAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button createOrderButton;
    TextView orderTotalText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShoppingCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createOrderButton = binding.createOrderButtonShoppingCart;
        orderRepository = OrderRepository.getInstance();
        shoppingCartOrder = orderRepository.getShoppingCartOrder();
        orderTotalText = binding.shoppingCartOrderTotal;
        orderTotalText.setText(String.valueOf(shoppingCartOrder.getOrderTotalSum()));
        recyclerView = binding.shoppingCartOrderedItemsRecyclerView;
        adapter = new OrderedItemsCartAdapter(shoppingCartOrder.getOrderedItems(), this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        createOrderButton.setOnClickListener(v ->
            orderRepository.create(shoppingCartOrder, new TextHttpResponseHandler() {

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Toast.makeText(ShoppingCartActivity.this, "Error: " + responseString , Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    orderRepository.resetCart();
                    shoppingCartOrder = orderRepository.getShoppingCartOrder();
                    Toast.makeText(ShoppingCartActivity.this, "Sent order!" , Toast.LENGTH_SHORT).show();
                    orderRepository.addNewModel(new RestSingleResponse<Order>(new TypeToken<Order>() {}, responseString).getData());
                    Intent intent = new Intent(ShoppingCartActivity.this, DisplayNewOrderActivity.class);
                    intent.putExtra("newOrderResponse", responseString);
                    startActivity(intent);
                    finish();

                }}, this));

    }

    @Override
    public void onItemClick(int pos) {
        orderTotalText.setText(String.valueOf(shoppingCartOrder.getOrderTotalSum()));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

}