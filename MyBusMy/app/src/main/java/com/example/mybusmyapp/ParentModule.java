package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ParentModule extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_module);

        TextView parent_nm=findViewById(R.id.textParentProfileName);
        TextView id=findViewById(R.id.textParentStudentID);

        Button childAttendBtn=findViewById(R.id.btnParentChildAttend);
        Button viewBusesBtn=findViewById(R.id.btnParentAllBuses);
        Button  bueTimeBtn=findViewById(R.id.btnParentViewBusTiming);
        Button logoutBtn=findViewById(R.id.btnParentLogout);

        parent_nm.setText(getIntent().getStringExtra("parent_name"));
        id.setText(getIntent().getStringExtra("studentid"));

        childAttendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ViewAttendanceForStudent.class);
                intent.putExtra("studentid",id.getText().toString());
                startActivity(intent);
            }
        });

        bueTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewBusDetails.class));
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ParentLogin.class));
                Toast.makeText(ParentModule.this, "Log out Successfully... ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        viewBusesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ParentModule.this,ViewDriver.class);
                startActivity(intent);

            }
        });

    }
}