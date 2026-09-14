package com.example.collegeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    private ImageView profileImage,notification,locatio,buses,student,driver,busroute,atten,feedback,report;
    private TextView profileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        profileImage=findViewById(R.id.imageView2);
        notification=findViewById(R.id.imageView5);
        locatio=findViewById(R.id.locationImageView51);
        buses=findViewById(R.id.locationImageView);
        student=findViewById(R.id.locationImageView9);
        driver=findViewById(R.id.locationImage1View8);
        busroute=findViewById(R.id.locationImageView5);
        atten=findViewById(R.id.locationImageView8);
        feedback=findViewById(R.id.locationImageView91);
        report=findViewById(R.id.locationImageView85);
        profileName=findViewById(R.id.textView3);



        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AddStudent.class));
            }
        });
        profileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, Stud_profile.class));
            }
        });
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, Stud_profile.class));
            }
        });

    }
}