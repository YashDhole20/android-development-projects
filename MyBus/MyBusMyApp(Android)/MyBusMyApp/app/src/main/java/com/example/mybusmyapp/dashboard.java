package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dashboard extends AppCompatActivity {

    Button admin,driver,student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        admin=(Button) findViewById(R.id.admin);
        driver=(Button) findViewById(R.id.driver);
        student=(Button) findViewById(R.id.student);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),AdminLogin.class);
                startActivity(in);
            }
        });
        driver.setOnClickListener(new View.OnClickListener() {
         @Override
              public void onClick(View v) {
             Intent in=new Intent(getApplicationContext(),MainActivity.class);
             startActivity(in);
    }
});
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),StudentSignup.class);
                startActivity(in);
            }
        });
    }
}