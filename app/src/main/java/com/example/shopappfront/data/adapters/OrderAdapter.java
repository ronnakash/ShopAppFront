package com.example.shopappfront.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.shopappfront.R;
import com.example.shopappfront.data.models.Order;
import com.example.shopappfront.data.viewholders.ModelViewHolder;
import com.example.shopappfront.data.viewholders.OrderViewHolder;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;

import java.util.List;

public class OrderAdapter extends ModelAdapterWithId<Order>{
    Context context;

    public OrderAdapter(List<Order> models, Context context, RecyclerViewInterface recyclerViewInterface) {
        super(models, recyclerViewInterface);
        this.context = context;
    }

    @NonNull
    @Override
    public ModelViewHolder<Order> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order, parent, false);
        return new OrderViewHolder(v, context, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder<Order> h, int i) {
        super.onBindViewHolder(h,i);
        OrderViewHolder holder = (OrderViewHolder) h;
        Order order = super.models.get(i);
        OrderedItemsListAdapter orderedItemsListAdapter = new OrderedItemsListAdapter(order.getOrderedItems());
        holder.orderedItemsRecyclerView.setAdapter(orderedItemsListAdapter);
        holder.orderId.setText(String.valueOf(order.getId()));
        holder.orderDate.setText(order.getTimeString());
        holder.orderTotal.setText(order.getOrderTotalSum().toString());
        holder.orderStatus.setText(order.getStatus());
    }

}
