package com.example.cropdiseasedetection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class farmersection extends AppCompatActivity {

    private Button btnHelpForm;
    private Button btnShowSuggestions;
    private Button btnShareExperience;
    private Button btnAddPolicies;
    private Button btnLogout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmersection );

        btnHelpForm = findViewById(R.id.btnHelpForm);
        btnShowSuggestions = findViewById(R.id.btnShowSuggestions);
        btnShareExperience = findViewById(R.id.btnShareExperience);
        btnAddPolicies = findViewById(R.id.btnAddPolicies);
        btnLogout = findViewById(R.id.btnLogout);

        btnHelpForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(farmersection.this, HelpForm.class);
                startActivity(intent);
            }
        });

        btnShowSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(farmersection.this, suggestion.class);
                startActivity(intent);
            }
        });

        btnShareExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(farmersection.this, experience.class);
                startActivity(intent);
            }
        });

        btnAddPolicies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(farmersection.this, showpolicies.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(farmersection.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
