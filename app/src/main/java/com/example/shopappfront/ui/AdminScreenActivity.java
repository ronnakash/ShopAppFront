package com.example.shopappfront.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shopappfront.databinding.ActivityAdminScreenBinding;
import com.example.shopappfront.ui.activities.ShopAppActivity;
import com.example.shopappfront.ui.show.ShowCategories;
import com.example.shopappfront.ui.show.ShowItems;
import com.example.shopappfront.ui.show.ShowOrders;
import com.example.shopappfront.ui.show.ShowUsers;

//public class AdminScreenActivity extends AppCompatActivity {
//
//    ActivityAdminScreenBinding binding;
//    Toolbar mainToolbar;
//    DrawerLayout drawerLayout;
//    Button itemsButton;
//    Button ordersButton;
//    Button usersButton;
//    Button categoriesButton;
//    NavigationView navigationView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityAdminScreenBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        drawerLayout = binding.drawerLayout;
//        mainToolbar = binding.mainToolbar;
//        itemsButton = binding.itemsButtonAdmin;
//        ordersButton = binding.ordersButtonAdmin;
//        usersButton = binding.usersButtonAdmin;
//        categoriesButton = binding.categoriesButtonAdmin;
//        mainToolbar.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
//        navigationView = binding.navigationView;
//        navigationView.setNavigationItemSelectedListener(item -> false);
//        itemsButton.setOnClickListener(v -> pressItemsButton(v));
//        ordersButton.setOnClickListener(v -> pressOrdersButton(v));
//        usersButton.setOnClickListener(v -> pressUsersButton(v));
//        categoriesButton.setOnClickListener(v -> pressCategoriesButton(v));
//
//
//    }
//
//    private void pressItemsButton(View v) {
//        Intent intent = new Intent(this, ShowItems.class);
//        startActivity(intent);
//    }
//
//    private void pressOrdersButton(View v) {
//        Intent intent = new Intent(this, ShowOrders.class);
//        startActivity(intent);
//    }
//
//    private void pressUsersButton(View v) {
//        Intent intent = new Intent(this, ShowUsers.class);
//        startActivity(intent);
//    }
//
//    private void pressCategoriesButton(View v) {
//        Intent intent = new Intent(this, ShowCategories.class);
//        startActivity(intent);
//    }
//
//
//
//}


public class AdminScreenActivity extends ShopAppActivity {

    ActivityAdminScreenBinding binding;
    Button itemsButton;
    Button ordersButton;
    Button usersButton;
    Button categoriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsButton = binding.itemsButtonAdmin;
        ordersButton = binding.ordersButtonAdmin;
        usersButton = binding.usersButtonAdmin;
        categoriesButton = binding.categoriesButtonAdmin;
        itemsButton.setOnClickListener(v -> pressItemsButton(v));
        ordersButton.setOnClickListener(v -> pressOrdersButton(v));
        usersButton.setOnClickListener(v -> pressUsersButton(v));
        categoriesButton.setOnClickListener(v -> pressCategoriesButton(v));
    }

    @Override
    protected void setBinding() {
        binding = ActivityAdminScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void pressItemsButton(View v) {
        Intent intent = new Intent(this, ShowItems.class);
        startActivity(intent);
    }

    private void pressOrdersButton(View v) {
        Intent intent = new Intent(this, ShowOrders.class);
        startActivity(intent);
    }

    private void pressUsersButton(View v) {
        Intent intent = new Intent(this, ShowUsers.class);
        startActivity(intent);
    }

    private void pressCategoriesButton(View v) {
        Intent intent = new Intent(this, ShowCategories.class);
        startActivity(intent);
    }
}
