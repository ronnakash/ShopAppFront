package com.example.shopappfront.data.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.example.shopappfront.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopappfront.data.models.Order;

public class OrderViewHolder extends ModelViewHolder<Order> {

    public TextView orderId;
    public TextView orderDate;
    public TextView orderStatus;
    public TextView orderTotal;
    public RecyclerView orderedItemsRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    public OrderViewHolder(@NonNull View itemView, Context context, RecyclerViewInterface recyclerViewInterface) {
        super(itemView, recyclerViewInterface);
        orderId = itemView.findViewById(R.id.order_id_order_list_text);
        orderDate = itemView.findViewById(R.id.order_date_order_list_text);
        orderStatus = itemView.findViewById(R.id.order_status_order_list_text);
        orderTotal = itemView.findViewById(R.id.order_total_amount_order_list_text);
        orderedItemsRecyclerView = itemView.findViewById(R.id.order_ordered_items_list_order_list);
        layoutManager = new LinearLayoutManager(context);
        orderedItemsRecyclerView.setHasFixedSize(false);
        orderedItemsRecyclerView.setLayoutManager(layoutManager);
    }

}
