package com.example.shopappfront.data.viewholders;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shopappfront.data.models.Item;

public class ItemViewHolder extends ModelViewHolder<Item> implements View.OnClickListener {

    public ImageView itemImage;
    public TextView itemName;
    public TextView itemCategories;
    public TextView itemOriginalPrice;
    public TextView itemFinalPrice;


    public ItemViewHolder(@NonNull View itemView, int itemImage, int itemName, int itemCategories,
                          int itemOriginalPrice, int itemFinalPrice, RecyclerViewInterface recyclerViewInterface) {
        super(itemView, recyclerViewInterface);
        this.itemImage = itemView.findViewById(itemImage);
        this.itemName = itemView.findViewById(itemName);
        this.itemCategories = itemView.findViewById(itemCategories);
        this.itemOriginalPrice = itemView.findViewById(itemOriginalPrice);
        this.itemFinalPrice = itemView.findViewById(itemFinalPrice);
    }

}
