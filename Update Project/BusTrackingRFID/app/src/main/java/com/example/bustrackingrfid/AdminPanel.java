package com.example.bustrackingrfid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        Button addStudBtn=findViewById(R.id.btnAddStudent);
        Button viewStudBtn=findViewById(R.id.btnViewStudent);
        Button presentBtn=findViewById(R.id.btnPresentStud);
        Button aboutus=findViewById(R.id.btnAboutus);
        Button logout=findViewById(R.id. btnLogout);


        addStudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this, StudentRegisteration.class));
            }
        });

        viewStudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Changes
                startActivity(new Intent(AdminPanel.this, ViewStudent.class));
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this, Dashboard.class));
            }
        });
        presentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Changed
                startActivity(new Intent(AdminPanel.this, PresentStudent.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this,  LoginOption.class));
                finish();
            }
        });
    }
}