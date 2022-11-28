package com.example.shopappfront.data.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shopappfront.data.models.OrderedItems;

public abstract class OrderedItemsViewHolder extends ModelViewHolder<OrderedItems>{

    public ImageView orderedItemsItemImage;
    public TextView orderedItemsItemName;
    public TextView orderedItemsItemFinalPrice;
    public TextView orderedItemsQuantity;
    public TextView orderedItemsTotalPrice;


    public OrderedItemsViewHolder(@NonNull View itemView, int itemImageId, int itemNameId, int itemFinalPriceId,
                                  int itemsQuantityId, int orderTotalPriceId) {
        super(itemView);
        this.orderedItemsItemImage = itemView.findViewById(itemImageId);
        this.orderedItemsItemName = itemView.findViewById(itemNameId);
        this.orderedItemsItemFinalPrice = itemView.findViewById(itemFinalPriceId);
        this.orderedItemsQuantity = itemView.findViewById(itemsQuantityId);
        this.orderedItemsTotalPrice = itemView.findViewById(orderTotalPriceId);

    }

}
