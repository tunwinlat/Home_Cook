package com.homecookapp.homecook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText tvName, tvEmail, tvPassword1, tvPassword2;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        tvName = findViewById(R.id.txtfieldUsername);
        tvEmail = findViewById(R.id.txtfieldEmail);
        tvPassword1 = findViewById(R.id.txtfieldPassword);
        tvPassword2 = findViewById(R.id.txtfieldReenterPassword);
        btnRegister = findViewById(R.id.btnRegister2);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvPassword1.getText().toString().equals(tvPassword2.getText().toString())){

                    userRegister();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                                    "Password mismatch!!",
                                    Toast.LENGTH_LONG)
                            .show();
                    tvPassword2.requestFocus();
                }
            }
        });



    }

    private void userRegister(){

        String email = tvEmail.getText().toString();
        String password = tvPassword1.getText().toString();
        String name = tvName.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        FirebaseUser mUser = mAuth.getCurrentUser();

                        RWUsers writeUserDetails = new RWUsers(name);

                        //DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference referenceProfile = database.getReference("Registered Users");

                        referenceProfile.child(mUser.getUid()).setValue(writeUserDetails.getName()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(getApplicationContext(),
                                                "Registration successful!",
                                                Toast.LENGTH_LONG)
                                        .show();

                                Intent intent
                                        = new Intent(RegisterActivity.this,
                                        HomeActivity.class);
                                startActivity(intent);
                            }
                        });


                    }
                    else {

                        try{
                            throw task.getException();
                        }
                        catch (FirebaseAuthWeakPasswordException e){

                            tvPassword1.setError("Your password is too short or easy to guess");
                            tvPassword1.requestFocus();
                        }
                        catch (FirebaseAuthInvalidCredentialsException e){
                            tvEmail.setError("Please enter a valid email address");
                            tvEmail.requestFocus();
                        }
                        catch (FirebaseAuthUserCollisionException e){
                            tvEmail.setError("An account with this email already exist");
                            tvEmail.requestFocus();
                        }
                        catch (Exception e){

                            Toast.makeText(
                                            getApplicationContext(),
                                            "Registration failed!!"
                                                    + " Please try again later",
                                            Toast.LENGTH_LONG)
                                    .show();

                        }





                    }

            }
        });

    }
}