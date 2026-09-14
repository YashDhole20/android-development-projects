package com.example.attendancesystemusinggps;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AttendanceActivity extends AppCompatActivity {

    Button btnview,btngive,btnprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        btnview=(Button)findViewById(R.id.btnview);
        btngive=(Button)findViewById(R.id.btngive);
        btnprofile=(Button)findViewById(R.id.btnprofile);

         btnview.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewAttendanceActivity.class);
                startActivity(intent);
             }
         });

         btngive.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(),GiveAttendanceActivity.class);
                 startActivity(intent);
             }
         });

         btnprofile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
             }
         });

    }
}
