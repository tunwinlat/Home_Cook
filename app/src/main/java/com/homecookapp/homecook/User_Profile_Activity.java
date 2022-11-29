package com.homecookapp.homecook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_Profile_Activity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView profile, email, accountType;
    Button update;
    EditText oldPassword, newPassword, confirmPassword;
    String userName, userEmail, userAccountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile = findViewById(R.id.profile_profile);
        email = findViewById(R.id.profile_email);
        accountType = findViewById(R.id.profile_accountType);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.newPasswordConfirm);
        update = findViewById(R.id.btnPasswordSubmit);

        mAuth = FirebaseAuth.getInstance();

        mUser = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference referenceProfile = database.getReference("RegisteredUsers");

        referenceProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                RWUsers rw = snapshot.child(mUser.getUid()).getValue(RWUsers.class);

                userName = rw.getName();
                userEmail = mUser.getEmail();
                userAccountType = rw.getAccountStatus();

                profile.setText(userName);
                email.setText("Email: "+userEmail);
                accountType.setText("Account Status: " + userAccountType);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthCredential credential = EmailAuthProvider
                        .getCredential(mUser.getEmail(), oldPassword.getText().toString());

// Prompt the user to re-provide their sign-in credentials
                mUser.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (newPassword.getText().toString().equals(confirmPassword.getText().toString())){
                                    mUser.updatePassword(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){
                                                Toast.makeText(getApplicationContext(),
                                                                "Password Successfully Updated!!",
                                                                Toast.LENGTH_LONG)
                                                        .show();
                                            }
                                        }
                                    });
                                }
                            }
                        });

            }
        });


    }
}