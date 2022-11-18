package com.homecookapp.homecook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.homecookapp.homecook.Domain.StoreDomain;

import java.util.ArrayList;

public class Store_Listing_Activity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewMerchantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_listing);

        recyclerViewMerchantList();
    }

    private void recyclerViewMerchantList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewMerchantList = findViewById(R.id.store_listing);
        ArrayList<StoreDomain>foodlist = new ArrayList<StoreDomain>();
        foodlist.add(new StoreDomain("cat_1", "Test", "Indian cusine", "Burnaby", "3/5"));
        foodlist.add(new StoreDomain("cat_1", "Test", "Indian cusine", "Burnaby", "3/5"));
        foodlist.add(new StoreDomain("cat_1", "Test", "Indian cusine", "Burnaby", "3/5"));
        foodlist.add(new StoreDomain("cat_1", "Test", "Indian cusine", "Burnaby", "3/5"));
        foodlist.add(new StoreDomain("cat_1", "Test", "Indian cusine", "Burnaby", "3/5"));
    }
}