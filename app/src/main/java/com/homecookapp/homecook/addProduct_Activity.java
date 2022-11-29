package com.homecookapp.homecook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addProduct_Activity extends AppCompatActivity {

    private String [] dishNames = new String[10];
    private String dishName = "nothing";
    private EditText quantity;
    private String [] ingredients;
    private EditText description;
    private Button submit;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Spinner dishSpinner;
    private FirebaseDatabase database;
    private DatabaseReference referenceProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        quantity = findViewById(R.id.ad_product_quantity);
        dishSpinner = findViewById(R.id.productSpinner);
        submit = findViewById(R.id.btnSubmit);

        database = FirebaseDatabase.getInstance();
        referenceProfile = database.getReference("Dishes");
        referenceProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                dishName = dataSnapshot.getValue().toString();

                dishNames = dishName.split("Ingredients");

                for (int i=1; i<dishNames.length-1; i++){
                    dishNames[i] = dishNames[i].substring(dishNames[i].indexOf("},") + 1, dishNames[i].indexOf("={"));
                    dishNames[i] = dishNames[i].substring(2);
                }
                dishNames[0] = dishNames[0].substring(1);
                dishNames[0] = dishNames[0].substring(0, dishNames[0].length() - 2);

                String [] dNames = new String[10];

                for (int i=0; i<dishNames.length-1; i++){
                    dNames[i] = dishNames[i];
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(addProduct_Activity.this, R.layout.spinner_item, dNames);

                dishSpinner.setAdapter(adapter);

                dishSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
//
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                        switch (position) {
                            case 0:
                                // Whatever you want to happen when the first item gets selected
                                break;
                            case 1:
                                // Whatever you want to happen when the second item gets selected
                                break;
                            case 2:
                                // Whatever you want to happen when the thrid item gets selected
                                break;

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(addProduct_Activity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });


        for(int i=0; i<dishNames.length -1; i++){
            System.out.println(dishNames[i]);
            System.out.println("second");
        }






    }

}