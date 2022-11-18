package com.homecookapp.homecook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.homecookapp.homecook.Adapter.ProductListingAdapter;
import com.homecookapp.homecook.Domain.ProductDomain;

import java.util.ArrayList;

public class Product_listing_Activity extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        recyclerViewProduct();
    }

    private void recyclerViewProduct() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewProductList = findViewById(R.id.product_listing2);
        recyclerViewProductList.setLayoutManager(linearLayoutManager);

        ArrayList<ProductDomain> productList = new ArrayList<>();
        productList.add(new ProductDomain("Pizza", "cat_1", "Main","16.5", "1"));
        productList.add(new ProductDomain("Burger", "cat_1", "Side","7.5", "5"));
        productList.add(new ProductDomain("Hotdog", "cat_1", "Side","5.5", "4"));
        productList.add(new ProductDomain("Drink", "cat_1", "Drink","3.5", "12"));
        productList.add(new ProductDomain("Bread", "cat_1", "Side","6.5", "3"));

        adapter=new ProductListingAdapter(productList);
        recyclerViewProductList.setAdapter(adapter);

    }
}