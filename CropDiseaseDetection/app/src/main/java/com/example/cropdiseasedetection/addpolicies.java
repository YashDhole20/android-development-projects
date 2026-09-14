package com.example.cropdiseasedetection;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class addpolicies extends AppCompatActivity {
    TextInputEditText EditPolicyTitle,EditPolicyDescription;
    AppCompatButton btnAddPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpolicies);

        EditPolicyTitle=(TextInputEditText) findViewById(R.id.EditPolicyTitle);
        EditPolicyDescription=(TextInputEditText) findViewById(R.id.EditPolicyDescription);
        btnAddPolicy=(AppCompatButton) findViewById(R.id.btnAddPolicy);


        btnAddPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                policiess();
            }
        });
    }
    private void policiess() {
        final String title = EditPolicyTitle.getText().toString().trim();
        final String description = EditPolicyDescription.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            EditPolicyTitle.setError("Please enter name");
            EditPolicyTitle.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(description)) {
            EditPolicyDescription.setError("Please enter address");
            EditPolicyDescription.requestFocus();
            return;
        }


        HashMap<String, String> params = new HashMap<>();

        params.put("title", title);
        params.put("description", description);

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //returing the response
        String id = Network.connect("http://"+Network.IP+"/policies.php", params);
        //System.out.println("resp" + id);
        if (id.trim() == "0") {
            Toast.makeText(getApplicationContext(), "Wrong Info", Toast.LENGTH_LONG).show();

        } else {
            Intent in = new Intent(getApplicationContext(), agriculture_section.class);
            startActivity(in);
        }
    }
}
