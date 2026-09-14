package com.example.attendanceusinggeofencing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDashboardActivity extends AppCompatActivity {

    Button btnview,btnstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btnview=(Button)findViewById(R.id.btnview);
        btnstatus=(Button)findViewById(R.id.btnstatus);

        btnstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StatusActivity.class);
                startActivity(intent);
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewAttendanceActivity.class);
                startActivity(intent);
            }
        });
    }
}
