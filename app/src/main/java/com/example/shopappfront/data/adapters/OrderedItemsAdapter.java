package com.example.shopappfront.data.adapters;

import androidx.annotation.NonNull;
import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.data.models.OrderedItems;
import com.example.shopappfront.data.viewholders.ModelViewHolder;
import com.example.shopappfront.data.viewholders.OrderedItemsViewHolder;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public abstract class OrderedItemsAdapter extends ModelAdapter<OrderedItems>{

    public OrderedItemsAdapter(List<OrderedItems> models, RecyclerViewInterface recyclerViewInterface) {
        super(models, recyclerViewInterface);
    }

    public OrderedItemsAdapter(List<OrderedItems> models) {
        super(models);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder<OrderedItems> h, int i) {
        OrderedItems orderedItems = super.models.get(i);
        Item item = orderedItems.getItem();
        OrderedItemsViewHolder holder = (OrderedItemsViewHolder) h;
        holder.itemView.setTag(item.getId());
        if (item.getPicUrl() != null)
            Picasso.get().load(item.getPicUrl()).into(holder.orderedItemsItemImage);
        holder.orderedItemsItemName.setText(item.getItemName());
        holder.orderedItemsItemFinalPrice.setText(String.valueOf(item.getItemFinalPrice()));
        holder.orderedItemsQuantity.setText(String.valueOf(orderedItems.getQuantity()));
        holder.orderedItemsTotalPrice.setText(String.valueOf(orderedItems.getTotalPrice()));
    }

}
