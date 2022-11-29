package com.homecookapp.homecook;

import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.homecookapp.homecook.Adapter.CategoryAdpater;
import com.homecookapp.homecook.Domain.CategoryDomain;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewCategoryList;
    TextView title1, title2, title3, content1, content2, content3;
    int carryID;

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        title1 = findViewById(R.id.tvTitle1);
        title2 = findViewById(R.id.tvTitle2);
        title3 = findViewById(R.id.tvTitle3);
        content1 = findViewById(R.id.tvContent1);
        content2 = findViewById(R.id.tvContent2);
        content3 = findViewById(R.id.tvContent3);

        FirebaseDatabase database;
        DatabaseReference referenceProfile;

        database = FirebaseDatabase.getInstance();
        referenceProfile = database.getReference("Posts");
        referenceProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                String allPost = dataSnapshot.getValue().toString();

                String [] postID = allPost.split("description");

                for (int i=1; i<postID.length-1; i++){
                    postID[i] = postID[i].substring(postID[i].indexOf("},") + 1, postID[i].indexOf("={"));
                    postID[i] = postID[i].substring(2);
                }
                postID[0] = postID[0].substring(1);
                postID[0] = postID[0].substring(0, postID[0].indexOf("={"));

                String [] forTitle = new String[postID.length];
                String [] forContent = new String[postID.length];

                System.out.println("THIS IS:::"+allPost);
                for (int i=0; i<postID.length; i++){
                    System.out.println("THIS IS:::"+postID[i]);
                }

                for (int i=0; i<postID.length -1; i++){
                    NewPost np = dataSnapshot.child(postID[i]).getValue(NewPost.class);
                    forTitle[i] = np.getName();
                    forContent[i] = np.getDescription();
                }



                title1.setText(forTitle[0]);
                title2.setText(forTitle[1]);
                title3.setText(forTitle[2]);

                content1.setText(forContent[0]);
                content2.setText(forContent[1]);
                content3.setText(forContent[2]);

                nav = findViewById(R.id.bottomNav);

                nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                                break;
                            case R.id.nav_like:
                                startActivity(new Intent(HomeActivity.this, Merchant_Inventory_Activity.class));
                                break;
                            case R.id.nav_profile:
                                startActivity(new Intent(HomeActivity.this, User_Profile_Activity.class));
                                break;
                            case R.id.nav_setting:
                                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                                break;
                            default:
                                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        return true;
                    }
                    return false;}
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(HomeActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

        title1.setOnClickListener(v -> {


            carryID = 1;
            Intent intent
                    = new Intent(HomeActivity.this,
                    Product_detail_Activity1.class);
            startActivity(intent);
        });

        title2.setOnClickListener(v -> {


            carryID = 2;
            Intent intent
                    = new Intent(HomeActivity.this,
                    Product_detail_Activity2.class);
            startActivity(intent);
        });

        title3.setOnClickListener(v -> {


            carryID = 3;
            Intent intent
                    = new Intent(HomeActivity.this,
                    Product_detail_Activity3.class);
            startActivity(intent);
        });


        recyclerViewCategory();
    }

    public int getCarryID(){
        return carryID;
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.categoryView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza", "cat_1"));
        categoryList.add(new CategoryDomain("Burger", "cat_2"));
        categoryList.add(new CategoryDomain("Hotdog", "cat_3"));
        categoryList.add(new CategoryDomain("Drink", "cat_4"));
        categoryList.add(new CategoryDomain("Bread", "cat_5"));

        adapter=new CategoryAdpater(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);

    }
}