package com.homecookapp.homecook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Merchant_Inventory_Activity extends AppCompatActivity {
    BottomNavigationView nav;
    Button addproductbtn = findViewById(R.id.btnAddProduct);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_inventory);

        addproductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Merchant_Inventory_Activity.this, addProduct_Activity.class));

            }
        });

        nav = findViewById(R.id.bottomNav);
        nav.setSelectedItemId(R.id.nav_home);
        nav.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(Merchant_Inventory_Activity.this, HomeActivity.class));
                        break;
                    case R.id.nav_like:
                        startActivity(new Intent(Merchant_Inventory_Activity.this, Merchant_Inventory_Activity.class));
                        break;
                    case R.id.nav_profile:
                        startActivity(new Intent(Merchant_Inventory_Activity.this, User_Profile_Activity.class));
                        break;
                    case R.id.nav_setting:
                        Toast.makeText(Merchant_Inventory_Activity.this, "Feature Coming Soon", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        startActivity(new Intent(Merchant_Inventory_Activity.this, HomeActivity.class));
                        return true;
                }
                return false;}
        });


    }
}