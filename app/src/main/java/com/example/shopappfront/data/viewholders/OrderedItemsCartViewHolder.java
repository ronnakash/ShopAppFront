package com.example.shopappfront.data.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shopappfront.R;
import androidx.annotation.NonNull;

public class OrderedItemsCartViewHolder extends OrderedItemsViewHolder{

    public TextView orderedItemsItemOriginalPrice;
    public Button incrementQuantityButton;
    public Button decrementQuantityButton;
    public ImageView removeIcon;

    public OrderedItemsCartViewHolder(@NonNull View itemView) {
        super(itemView, R.id.ordered_items_cart_image, R.id.ordered_items_cart_name_text,
                R.id.ordered_items_cart_final_price_text, R.id.ordered_items_cart_quantity_text,
                R.id.ordered_items_cart_total_price_text);
        orderedItemsItemOriginalPrice = itemView.findViewById(R.id.ordered_items_cart_original_price_text);
        incrementQuantityButton = itemView.findViewById(R.id.increment_quantity_button);
        decrementQuantityButton = itemView.findViewById(R.id.decrement_quantity_button);
        removeIcon = itemView.findViewById(R.id.ordered_items_cart_remove_item_button);

    }

}
