package com.example.shopappfront.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.shopappfront.R;
import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.data.models.OrderedItems;
import com.example.shopappfront.data.viewholders.ModelViewHolder;
import com.example.shopappfront.data.viewholders.OrderedItemsListViewHolder;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;

import java.util.List;

public class OrderedItemsListAdapter extends OrderedItemsAdapter{
    public OrderedItemsListAdapter(List<OrderedItems> models) {
        super(models);
    }

    public OrderedItemsListAdapter(List<OrderedItems> models, RecyclerViewInterface recyclerViewInterface) {
        super(models, recyclerViewInterface);
    }

    @NonNull
    @Override
    public ModelViewHolder<OrderedItems> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ordered_items_order, parent, false);
        return new OrderedItemsListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder<OrderedItems> h, int i) {
        super.onBindViewHolder(h,i);
        OrderedItems orderedItems = super.models.get(i);
        Item item = orderedItems.getItem();
        OrderedItemsListViewHolder holder = (OrderedItemsListViewHolder) h;
        holder.orderedItemsItemDescription.setText(String.valueOf(item.getItemDescription()));
    }
}
