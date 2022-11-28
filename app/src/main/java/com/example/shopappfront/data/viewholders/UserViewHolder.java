package com.example.shopappfront.data.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.example.shopappfront.R;
import com.example.shopappfront.data.models.User;

public class UserViewHolder extends ModelViewHolder<User>{
    public TextView username;
    public TextView userFullName;
    public TextView userPermissions;
    public ImageView userPic;
//    public RecyclerView orderedItemsRecyclerView;
//    RecyclerView.LayoutManager layoutManager;

    public UserViewHolder(@NonNull View itemView, Context context, RecyclerViewInterface recyclerViewInterface) {
        super(itemView, recyclerViewInterface);
        username = itemView.findViewById(R.id.user_username_list_users);
        userFullName = itemView.findViewById(R.id.user_full_name_list_users);
        userPermissions = itemView.findViewById(R.id.user_permissions_list_users);
//        orderedItemsRecyclerView = itemView.findViewById(R.id.order_ordered_items_list_order_list);
//        layoutManager = new LinearLayoutManager(context);
//        orderedItemsRecyclerView.setHasFixedSize(false);
//        orderedItemsRecyclerView.setLayoutManager(layoutManager);
        userPic = itemView.findViewById(R.id.user_image_list_users);
    }

}
