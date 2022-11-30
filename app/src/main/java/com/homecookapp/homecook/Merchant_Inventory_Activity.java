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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Merchant_Inventory_Activity extends AppCompatActivity {
    BottomNavigationView nav;
    Button addproductbtn;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String name, type;
    TextView merchantName, merchantStatus;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_inventory);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        addproductbtn = findViewById(R.id.btnAddProduct);

        name = "";
        type = "";

        merchantName = findViewById(R.id.merchantInventoryTitle);
        merchantStatus = findViewById(R.id.merchantStatus);



        FirebaseDatabase database;
        DatabaseReference referenceProfile;

        database = FirebaseDatabase.getInstance();
        referenceProfile = database.getReference("RegisteredUsers");
        referenceProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                RWUsers rw = snapshot.child(mUser.getUid()).getValue(RWUsers.class);
                //NewPost np = snapshot.child(mUser.getUid()).getValue(NewPost.class);

                name = rw.getName();
                type = rw.getAccountStatus();

                merchantName.setText(name);
                merchantStatus.setText("Account Status: "+type);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addproductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Merchant_Inventory_Activity.this, addProduct_Activity.class);
                startActivity(i);
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