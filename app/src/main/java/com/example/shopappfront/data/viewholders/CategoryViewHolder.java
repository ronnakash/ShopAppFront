package com.example.shopappfront.data.viewholders;



import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shopappfront.data.adapters.SwitchListInterface;
import com.example.shopappfront.data.models.Category;

public class CategoryViewHolder extends ModelViewHolder<Category>{

    public TextView categoryName;
    public Category category;
    SwitchListInterface<Category> switchListInterface;

    public CategoryViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface,
                              int category_list_layout_id, SwitchListInterface<Category> switchListInterface) {
        super(itemView, recyclerViewInterface);
        this.categoryName = itemView.findViewById(category_list_layout_id);
        if (categoryName instanceof Switch && switchListInterface != null){
            Switch categorySwitch = (Switch) categoryName;
            this.switchListInterface = switchListInterface;
            categorySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                switchListInterface.onSwitch(isChecked, category);
                //categorySwitch.setChecked(isChecked);
            });
        }
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
