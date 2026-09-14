package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class StudentProfile extends AppCompatActivity {

    private TextView profilename,mobileno;
    private Button viewEpassBtn,viewAttenBtn,viewBusBtn,studLogoutBtn;
    private ImageView imageView;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        imageView=findViewById(R.id.imageStudentLogo);
        profilename=findViewById(R.id.textStudentProfileName);
        mobileno=findViewById(R.id.textStudentProfilePhone);
        viewBusBtn=findViewById(R.id.btnViewBusDtails);
        viewAttenBtn=findViewById(R.id.btnViewAttendanceStu);
        viewEpassBtn=findViewById(R.id.btnViewEpass);
        studLogoutBtn=findViewById(R.id.btnStudentLogout);
        profilename.setText(getIntent().getStringExtra("name") );
        mobileno.setText(getIntent().getStringExtra("mobileno"));

        id=getIntent().getStringExtra("studentid");

        viewEpassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent=new Intent(getApplicationContext(), ShowQRCode.class);
                    intent.putExtra("studentid",id);
                    startActivity(intent);
            }
        });

        studLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                Toast.makeText(StudentProfile.this, "Logout Successful...", Toast.LENGTH_SHORT).show();
            }
        });

        viewAttenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),    ViewAttendanceForStudent.class);
                intent.putExtra("studentid",id);
                startActivity(intent);
            }
        });

        viewBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentProfile.this, ViewDriver.class);
                startActivity(intent);
            }
        });

    }
}