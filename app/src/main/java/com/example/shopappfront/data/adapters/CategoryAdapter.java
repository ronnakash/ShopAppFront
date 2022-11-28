package com.example.shopappfront.data.adapters;


import androidx.annotation.NonNull;
import com.example.shopappfront.data.models.Category;
import com.example.shopappfront.data.viewholders.CategoryViewHolder;
import com.example.shopappfront.data.viewholders.ModelViewHolder;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;

import java.util.List;

public abstract class CategoryAdapter extends ModelAdapterWithId<Category>{

    public CategoryAdapter(List<Category> models, RecyclerViewInterface recyclerViewInterface) {
        super(models, recyclerViewInterface);
    }

    public CategoryAdapter(List<Category> models) {
        super(models);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder<Category> h, int i) {
        super.onBindViewHolder(h,i);
        Category category = super.models.get(i);
        CategoryViewHolder holder = (CategoryViewHolder) h;
        holder.itemView.setTag(category.getId());
        holder.setCategory(category);
    }


}
