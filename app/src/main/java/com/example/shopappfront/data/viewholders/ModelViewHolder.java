package com.example.shopappfront.data.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopappfront.data.models.ApplicationModel;

public abstract class ModelViewHolder<T extends ApplicationModel> extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    RecyclerViewInterface recyclerViewInterface;

    public ModelViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        this.recyclerViewInterface = recyclerViewInterface;
        itemView.setOnClickListener(this);
    }

    public ModelViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    @Override
    public void onClick(View v) {
        if (recyclerViewInterface != null){
            int pos = getAdapterPosition();
            recyclerViewInterface.onItemClick(pos);
        }
    }



}
