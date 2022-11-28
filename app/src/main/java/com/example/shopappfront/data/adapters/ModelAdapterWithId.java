package com.example.shopappfront.data.adapters;

import androidx.annotation.NonNull;

import com.example.shopappfront.data.models.ApplicationModelWithId;
import com.example.shopappfront.data.viewholders.ModelViewHolder;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;

import java.util.List;


public abstract class ModelAdapterWithId<T extends ApplicationModelWithId> extends ModelAdapter<T>{

    public ModelAdapterWithId(List<T> models, RecyclerViewInterface recyclerViewInterface) {
        super(models, recyclerViewInterface);
    }

    public ModelAdapterWithId(List<T> models) {
        super(models);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder<T> h, int i) {
        T model = super.models.get(i);
        h.itemView.setTag(model.getId());
    }



}
