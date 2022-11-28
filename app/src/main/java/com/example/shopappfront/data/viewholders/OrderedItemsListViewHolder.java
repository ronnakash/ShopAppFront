package com.example.shopappfront.data.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shopappfront.R;

public class OrderedItemsListViewHolder extends OrderedItemsViewHolder{

        public TextView orderedItemsItemDescription;

    public OrderedItemsListViewHolder(@NonNull View itemView) {
        super(itemView, R.id.ordered_items_order_image, R.id.ordered_items_order_name_text,
                R.id.ordered_items_order_final_price_text, R.id.ordered_items_order_quantity_text,
                R.id.ordered_items_order_total_price_text);
        this.orderedItemsItemDescription = itemView.findViewById(R.id.ordered_items_order_description_text);

    }
}
