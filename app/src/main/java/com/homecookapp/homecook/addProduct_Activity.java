package com.homecookapp.homecook;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.TimeUnit;

import pub.devrel.easypermissions.EasyPermissions;

public class addProduct_Activity extends AppCompatActivity {

    private String [] dishNames = new String[10];
    private String dishName = "nothing";
    private EditText quantity;
    private String [] ingredients;
    private EditText description;
    private Button submit, select;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Spinner dishSpinner;
    private FirebaseDatabase database;
    private DatabaseReference referenceProfile;
    private TextView listIngredients;
    Uri selectedImage, downloadUri;

    public void setSelectedImage(Uri result){
        selectedImage = result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        quantity = findViewById(R.id.ad_product_quantity);
        dishSpinner = findViewById(R.id.productSpinner);
        submit = findViewById(R.id.btnSubmit);
        select = findViewById(R.id.btnSelectImage);
        listIngredients = findViewById(R.id.tvListIngredients);
        description = findViewById(R.id.product_Description);
        selectedImage = Uri.EMPTY;
        downloadUri = Uri.EMPTY;

        //Uri selectedImage2 = Uri.EMPTY;

        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        selectedImage = uri;
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        StorageReference riversRef = storageRef.child("images/"+selectedImage.getLastPathSegment());
                        UploadTask uploadTask = riversRef.putFile(selectedImage);

                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                                // ...
                            }
                        });
                        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }

                                // Continue with the task to get the download URL
                                return riversRef.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    downloadUri = task.getResult();
                                } else {
                                    // Handle failures
                                    // ...
                                }
                            }
                        });
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });


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
                        String selectedItem = dNames[position];
                        String ingredients = dataSnapshot.child(selectedItem).getValue().toString();
                        ingredients = ingredients.substring(13);
                        ingredients = ingredients.substring(0, ingredients.length() - 1);
                        //Toast.makeText(addProduct_Activity.this, ingredients, Toast.LENGTH_SHORT).show();
                        listIngredients.setText(ingredients);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



                select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    pickMedia.launch(new PickVisualMediaRequest.Builder()
                                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                                .build());

                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        System.out.println(downloadUri.toString());
                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(addProduct_Activity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });






    }

}