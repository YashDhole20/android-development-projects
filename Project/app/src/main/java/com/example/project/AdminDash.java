package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminDash extends AppCompatActivity {
    private ImageView profileImage,notification,locatio,buses,student,driver,busroute,atten,feedback,report;
    private TextView profileName;
    private Button addStudentBtn,viewStudentBtn,deleteStudentBtn;
    private ImageButton studentCloseBtn,busCloseBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);

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
                Dialog dialog = new Dialog(AdminDash.this);
                dialog.setContentView(R.layout.student_details_popup);

                //Student   btn
                addStudentBtn=dialog.findViewById(R.id.btnAddStudent);
                viewStudentBtn=dialog.findViewById(R.id.btnViewStudent);
                deleteStudentBtn=dialog.findViewById(R.id.btnDeleteStudent);
                studentCloseBtn = dialog.findViewById(R.id.imageBackToPanelStudent);
                studentCloseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                addStudentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), AddStudent.class));
                        dialog.dismiss();
                        finish();
                    }
                });
                viewStudentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), ViewStudentDetails.class));
                        dialog.dismiss();
                    }
                });
                deleteStudentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), DeleteStudentDetails.class));
                        dialog.dismiss();
                    }
                });
            }
        });
        profileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDash.this, AdminProfile.class));
            }
        });
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDash.this, AdminProfile.class));
            }
        });

    }
}