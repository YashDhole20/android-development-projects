package com.example.cropdiseasedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Dashboard extends AppCompatActivity {

    Button farmer, agr,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        farmer=(Button) findViewById(R.id.farmer);
        agr=(Button) findViewById(R.id.agr);
        admin=(Button)findViewById(R.id.adminlog);


        farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), farmer_login.class);
                startActivity(intent);
            }
        });

        agr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), agriculture_login.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(intent);
            }

        });
    }
}