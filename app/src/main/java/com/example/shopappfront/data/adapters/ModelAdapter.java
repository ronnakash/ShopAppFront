package com.example.shopappfront.data.adapters;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shopappfront.data.models.ApplicationModel;
import com.example.shopappfront.data.viewholders.ModelViewHolder;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;

import java.util.List;

public abstract class ModelAdapter<T extends ApplicationModel> extends RecyclerView.Adapter<ModelViewHolder<T>> {

    protected List<T> models;
    RecyclerViewInterface recyclerViewInterface;


    public ModelAdapter(List<T> models, RecyclerViewInterface recyclerViewInterface) {
        super();
        this.models = models;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    public ModelAdapter(List<T> models) {
        super();
        this.models = models;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setModels(List<T> models) {
        this.models = models;
        this.notifyDataSetChanged();
    }

}

