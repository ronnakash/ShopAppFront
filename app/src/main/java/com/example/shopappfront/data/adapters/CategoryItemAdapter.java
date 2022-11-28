package com.example.shopappfront.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

import com.example.shopappfront.R;
import com.example.shopappfront.data.models.Category;
import com.example.shopappfront.data.viewholders.CategoryViewHolder;
import com.example.shopappfront.data.viewholders.ModelViewHolder;

import java.util.List;
public class CategoryItemAdapter extends CategoryAdapter implements SwitchListInterface<Category>
{

    List<Category> marked;


    public CategoryItemAdapter(List<Category> models, List<Category> marked) {
        super(models);
        this.marked = marked;
    }

    @NonNull
    @Override
    public ModelViewHolder<Category> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_categories_edit_item, parent,false);
        return new CategoryViewHolder(v, recyclerViewInterface, R.id.category_name_switch_categories_item_list, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder<Category> h, int i) {
        super.onBindViewHolder(h,i);
        Category category = super.models.get(i);
        CategoryViewHolder holder = (CategoryViewHolder) h;
        Switch switchCategory = ((Switch) holder.categoryName);
        switchCategory.setText(category.getCategoryName());
        for (int j = 0; j < marked.size(); j++){
            Category markedCategory = marked.get(j);
            if(category.getId() == markedCategory.getId()){
                switchCategory.setChecked(true);
            }
        }
    }

    @Override
    public void onSwitch(boolean isChecked, Category model) {
        if (model == null)
            return;
        for (int i = 0; i < marked.size(); i++) {
            Category markedCategory = marked.get(i);
            if (markedCategory.getId() == model.getId()){
                if (!isChecked)
                    marked.remove(i);
                return;
            }
        }
        marked.add(model);
    }

    public List<Category> getMarked() {
        return marked;
    }
}
