package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NoRecord extends AppCompatActivity {

    private TextView norecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_record);

        norecord=findViewById(R.id.textNoRecord);
    }
}