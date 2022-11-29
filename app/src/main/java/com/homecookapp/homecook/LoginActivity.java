package com.homecookapp.homecook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity {

    private EditText tvEmail, tvPassword;
    private TextView tvRegister;
    private Button btnLogin;
    //private ProgressBar pgBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        tvEmail = findViewById(R.id.txtRegEmail);
        tvPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin2);
        tvRegister = findViewById(R.id.tvRegisterq);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                userLogin();
            }
        });
        tvRegister.setOnClickListener(v -> {

            Intent intent
                    = new Intent(LoginActivity.this,
                    RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void userLogin(){
        String email = tvEmail.getText().toString();
        String password = tvPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            tvEmail.setError("Please enter a valid email address");
            tvEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            tvPassword.setError("Password field cannot be empty");
            tvPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();


                                    Intent intent
                                            = new Intent(LoginActivity.this,
                                            HomeActivity.class);
                                    startActivity(intent);
                                }

                                else {


                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_LONG)
                                            .show();


                                }
                            }
                        });
    }
}