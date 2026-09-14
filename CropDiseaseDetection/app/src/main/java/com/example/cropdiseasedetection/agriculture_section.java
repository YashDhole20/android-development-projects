package com.example.cropdiseasedetection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class agriculture_section extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_section);

        Button btnHelpRequestForm = findViewById(R.id.btnHelpRequestForm);
        Button btnAddPolicies = findViewById(R.id.btnAddPolicies);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnHelpRequestForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(agriculture_section.this,  replyform.class);
                startActivity(intent);
            }
        });

        btnAddPolicies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(agriculture_section.this, addpolicies.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(agriculture_section.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}