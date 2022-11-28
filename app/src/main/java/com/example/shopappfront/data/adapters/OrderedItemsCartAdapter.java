package com.example.shopappfront.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.shopappfront.R;
import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.data.models.OrderedItems;
import com.example.shopappfront.data.viewholders.ModelViewHolder;
import com.example.shopappfront.data.viewholders.OrderedItemsCartViewHolder;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;

import java.util.List;


public class OrderedItemsCartAdapter extends OrderedItemsAdapter {
    RecyclerViewInterface recyclerViewUpdateTotalInterface;

    public OrderedItemsCartAdapter(List<OrderedItems> models, RecyclerViewInterface recyclerViewInterface) {
        super(models);
        recyclerViewUpdateTotalInterface = recyclerViewInterface;
    }

    public OrderedItemsCartAdapter(List<OrderedItems> models) {
        super(models);
    }

    @NonNull
    @Override
    public ModelViewHolder<OrderedItems> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ordered_items_cart, parent, false);
        return new OrderedItemsCartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder<OrderedItems> h, int i) {
        super.onBindViewHolder(h,i);
        OrderedItems orderedItems = super.models.get(i);
        Item item = orderedItems.getItem();
        OrderedItemsCartViewHolder holder = (OrderedItemsCartViewHolder) h;
        holder.itemView.setTag(item.getId());
        holder.orderedItemsItemOriginalPrice.setText(String.valueOf(item.getItemBasePrice()));
        holder.incrementQuantityButton.setOnClickListener(v -> {
            orderedItems.setQuantity(orderedItems.getQuantity()+1);
            notifyItemChanged(i);
            updateOrderTotal();
        });
        holder.decrementQuantityButton.setOnClickListener(v -> {
            int q = orderedItems.getQuantity();
            if (q>1) {
                orderedItems.setQuantity(q - 1);
                notifyItemChanged(i);
                updateOrderTotal();
            }
        });
        holder.removeIcon.setOnClickListener(v -> {
            super.models.remove(i);
            super.notifyDataSetChanged();
            updateOrderTotal();
        });
    }

    private void updateOrderTotal() {
        if (recyclerViewUpdateTotalInterface != null){
            recyclerViewUpdateTotalInterface.onItemClick(-1);
        }

    }

}
