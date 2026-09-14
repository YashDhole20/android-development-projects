package com.example.ecochargehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class station_login extends AppCompatActivity {

    EditText mobileEditText, passwordEditText;
    Button loginButton;
    TextView registerText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroom_login);

        mobileEditText = findViewById(R.id.mobileEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerText = findViewById(R.id.registerText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobileEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
                } else {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("email", mobileEditText.getText().toString());
                    param.put("password", passwordEditText.getText().toString());

                    StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(sb);
                    String response = Network.connect("http://" + Network.IP + "/station_login.php", param);

                    if (response.trim().equals("0")) {
                        Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_LONG).show();
                    } else if (response.trim().equals("*")) {
                        Toast.makeText(getApplicationContext(), "Approval is pending. You cannot log in yet.", Toast.LENGTH_LONG).show();
                    } else if (response.trim().equals("&")) {
                        Toast.makeText(getApplicationContext(), "Your approval is discarded. You cannot log in.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), station_section.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open registration page when clicked
                Intent intent = new Intent(station_login.this, station_registration.class);
                startActivity(intent);
            }
        });
    }
}