package com.example.shopappfront.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.shopappfront.R;
import com.example.shopappfront.data.models.Category;
import com.example.shopappfront.data.viewholders.CategoryViewHolder;
import com.example.shopappfront.data.viewholders.ModelViewHolder;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;

import java.util.List;

public class CategoryListAdapter extends CategoryAdapter {
    public CategoryListAdapter(List<Category> models, RecyclerViewInterface recyclerViewInterface) {
        super(models, recyclerViewInterface);
    }

    @NonNull
    @Override
    public ModelViewHolder<Category> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_categories, parent,false);
        return new CategoryViewHolder(v, recyclerViewInterface, R.id.category_name_categories_list_text, null);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder<Category> h, int i) {
        super.onBindViewHolder(h,i);
        CategoryViewHolder holder = (CategoryViewHolder) h;
        Category category = super.models.get(i);
        holder.categoryName.setText(category.getCategoryName());
    }

}
