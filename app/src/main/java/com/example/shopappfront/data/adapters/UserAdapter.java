package com.example.shopappfront.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.shopappfront.R;
import com.example.shopappfront.data.models.User;
import com.example.shopappfront.data.viewholders.ModelViewHolder;
import com.example.shopappfront.data.viewholders.RecyclerViewInterface;
import com.example.shopappfront.data.viewholders.UserViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends ModelAdapterWithId<User> {
    Context context;

    public UserAdapter(List<User> models, Context context, RecyclerViewInterface recyclerViewInterface) {
        super(models, recyclerViewInterface);
        this.context = context;
    }

    @NonNull
    @Override
    public ModelViewHolder<User> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users, parent, false);
        return new UserViewHolder(v, context, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder<User> h, int i) {
        super.onBindViewHolder(h,i);
        UserViewHolder holder = (UserViewHolder) h;
        User user = super.models.get(i);
        holder.userFullName.setText(user.getFirstName() + " " + user.getLastName());
        holder.username.setText(user.getUsername());
        holder.userPermissions.setText(user.getPermissions());
        try {
            Picasso.get().load(user.getPicUrl()).into(holder.userPic);
        }
        catch (Exception e) {}
    }

}
