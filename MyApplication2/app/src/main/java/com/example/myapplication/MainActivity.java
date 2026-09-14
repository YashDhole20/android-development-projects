package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit;
    private AppCompatTextView tvUserName;
    private AppCompatTextView tvPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}