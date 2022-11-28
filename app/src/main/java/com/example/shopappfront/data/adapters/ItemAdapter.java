package com.example.shopappfront.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.shopappfront.R;
import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.data.viewholders.ItemViewHolder;
import com.example.shopappfront.data.viewholders.ModelViewHolder;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends ModelAdapterWithId<Item>{

    public ItemAdapter(List<Item> models, RecyclerViewInterface recyclerViewInterface) {
        super(models, recyclerViewInterface);
    }

    @NonNull
    @Override
    public ModelViewHolder<Item> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ItemViewHolder(v, R.id.item_image_list_items, R.id.item_name_text, R.id.item_categories_text,
                R.id.item_original_price_text, R.id.item_final_price_text, super.recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder<Item> h, int i) {
        super.onBindViewHolder(h,i);
        Item item = super.models.get(i);
        ItemViewHolder holder = (ItemViewHolder) h;
        holder.itemView.setTag(item.getId());
        if (item.getPicUrl() != null){
            try {
                Picasso.get().load(item.getPicUrl()).into(holder.itemImage);
            }
            catch (Exception e) {}
        }
        String categoriesString = "";
        List<String> categoriesList = item.getCategories();
        if (categoriesList.size()>0){
            categoriesString = categoriesList.get(0);
            for (int j = 1; j < item.getCategories().size(); j++)
                categoriesString = categoriesString +", " + categoriesList.get(j);
        }
        holder.itemCategories.setText(categoriesString);
        holder.itemName.setText(item.getItemName());
        holder.itemFinalPrice.setText(String.valueOf(item.getItemFinalPrice()));
        holder.itemOriginalPrice.setText(String.valueOf(item.getItemBasePrice()));
    }

}
