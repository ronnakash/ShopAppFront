package com.example.shopappfront.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class ShopAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
    }

    protected abstract void setBinding();

    protected void showError(Context context, String errorMessage){
        Toast.makeText(context, "Error: " + errorMessage , Toast.LENGTH_LONG).show();
    }

    protected void showSuccess(Context context, String message){
        Toast.makeText(context, message , Toast.LENGTH_SHORT).show();
    }


}
