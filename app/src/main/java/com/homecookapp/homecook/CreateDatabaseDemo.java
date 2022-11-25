package com.homecookapp.homecook;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateDatabaseDemo {

    private FirebaseAuth mAuth;

    private String tableName = "Dishes";
    private String primaryKey = "Curry";
    private String ingredients = "Onion,Garlic,Oil";



    public void setmAuth(FirebaseAuth _mAuth){
        this.mAuth = _mAuth;
    }

    public FirebaseAuth getmAuth(){
        return this.mAuth;
    }

    public void createDatabase(){

        TableContents contents = new TableContents(ingredients);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referenceProfile = database.getReference(tableName);

        referenceProfile.child(primaryKey).setValue(contents).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                
            }
        });

    }
}
