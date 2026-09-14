package com.apti.smartaptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {
Button btnstartexam;
Button btnresume;
Button btnlogot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnstartexam=findViewById(R.id.btnstartexam);

        btnstartexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Dashboard.this, CompanySelection.class);
                String id1=getIntent().getStringExtra("UserID");
                intent.putExtra("UserID",id1);
                System.out.println(id1);
                startActivity(intent);
            }
        });
        btnlogot=findViewById(R.id.btnlogout);
        btnlogot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent out = new Intent(getApplicationContext(), StudentLogin.class);
                startActivity(out);
                finish();
            }
        });
        btnresume = findViewById(R.id.btnresume);
        btnresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), ResumeGuidelinesActivity.class);
                 startActivity(in);
            }
        });
    }
}
