package com.homecookapp.homecook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnLoadDB = findViewById(R.id.btnLoadDB);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadRegister();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLogin();
            }
        });
        btnLoadDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadDatabase();
            }
        });

    }

    public void LoadRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void LoadLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void LoadDatabase(){

        CreateDatabaseDemo cdd = new CreateDatabaseDemo();
        cdd.createDatabase();
        Toast.makeText(getApplicationContext(),
                        "Database Successfully Loaded",
                        Toast.LENGTH_LONG)
                .show();
    }
}