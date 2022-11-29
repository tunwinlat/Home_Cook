package com.homecookapp.homecook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class Product_detail_Activity2 extends AppCompatActivity {

    TextView title, quantity, description, request;
    ImageView photo;
    BottomNavigationView nav;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        title = findViewById(R.id.productPage_PageTitle);
        quantity = findViewById(R.id.tvQuantity);
        description = findViewById(R.id.productPage_Description);
        photo = findViewById(R.id.imageView2);
        request = findViewById(R.id.btnRequest);

        HomeActivity ha = new HomeActivity();
        int rfID = ha.getCarryID();

        FirebaseDatabase database;
        DatabaseReference referenceProfile;

        nav = findViewById(R.id.bottomNav);
        nav.setSelectedItemId(R.id.nav_home);
        nav.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(Product_detail_Activity2.this, HomeActivity.class));
                        break;
                    case R.id.nav_like:
                        startActivity(new Intent(Product_detail_Activity2.this, Merchant_Inventory_Activity.class));
                        break;
                    case R.id.nav_profile:
                        startActivity(new Intent(Product_detail_Activity2.this, User_Profile_Activity.class));
                        break;
                    case R.id.nav_setting:
                        Toast.makeText(Product_detail_Activity2.this, "Feature Coming Soon", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        startActivity(new Intent(Product_detail_Activity2.this, HomeActivity.class));
                        return true;
                }
                return false;}
        });

        database = FirebaseDatabase.getInstance();
        referenceProfile = database.getReference("Posts");
        referenceProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


//                NewPost np = dataSnapshot.child(rfID).getValue(NewPost.class);
//                String sTitle = np.getName();
//                String sDescription = np.getDescription();
//                String sQuantity = np.getQuantity();
//                String sType = np.getType();
//                String sUri = np.getUri();
//                Uri realUri = Uri.parse(sUri);

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
                String [] forQuantity = new String[postID.length];
                String [] forUri = new String[postID.length];

                System.out.println("THIS IS:::"+allPost);
                for (int i=0; i<postID.length; i++){
                    System.out.println("THIS IS:::"+postID[i]);
                }

                System.out.println("RFID is:::::"+rfID);
                NewPost np = dataSnapshot.child(postID[1]).getValue(NewPost.class);
                forTitle[1] = np.getName();
                forContent[1] = np.getDescription();
                forQuantity[1] = np.getQuantity();
                forUri[1] = np.getUri();

                title.setText(forTitle[1]);
                description.setText(forContent[1]);
                quantity.setText(forQuantity[1]);
                //photo.setImageURI(Uri.parse(forUri[0]));

                new DownloadImageFromInternet((ImageView) findViewById(R.id.imageView2))
                        .execute(forUri[1]);


            }

            class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
                ImageView imageView;

                public DownloadImageFromInternet(ImageView imageView) {
                    this.imageView = imageView;
                    Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
                }

                protected Bitmap doInBackground(String... urls) {
                    String imageURL = urls[0];
                    Bitmap bimage = null;
                    try {
                        InputStream in = new java.net.URL(imageURL).openStream();
                        bimage = BitmapFactory.decodeStream(in);

                    } catch (Exception e) {
                        Log.e("Error Message", e.getMessage());
                        e.printStackTrace();
                    }
                    return bimage;
                }

                protected void onPostExecute(Bitmap result) {
                    imageView.setImageBitmap(result);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Product_detail_Activity2.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}