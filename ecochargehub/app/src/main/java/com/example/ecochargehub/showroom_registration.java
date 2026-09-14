package com.example.ecochargehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class showroom_registration extends AppCompatActivity {
    EditText nameEditText,emailEditText, passwordEditText, mobileEditText, cityEditText,talukaEditText,districtEditText;
    Button submitButton;
    TextView registerText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroom_registration);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        cityEditText = findViewById(R.id.cityEditText);
        submitButton = findViewById(R.id.submitButton);
        registerText = findViewById(R.id.registerText);
        talukaEditText = findViewById(R.id.talukaEditText);
        districtEditText = findViewById(R.id.districtEditText);

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showroom_registration.this, showroom_login.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String city = cityEditText.getText().toString().trim();
        String taluka = talukaEditText.getText().toString().trim();
        String district = talukaEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("Enter a station name");
            nameEditText.requestFocus();
            return;
        }
        if (!isValidEmail(email)) {
            emailEditText.setError("Enter a valid email");
            emailEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Please enter a password");
            passwordEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            mobileEditText.setError("Enter your mobile number");
            mobileEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(city)) {
            cityEditText.setError("Enter your city");
            cityEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(taluka)) {
            talukaEditText.setError("Enter your taluka");
            talukaEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(city)) {
            districtEditText.setError("Enter your district");
            districtEditText.requestFocus();
            return;
        }

        // Register the user
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        params.put("mobile", mobile);
        params.put("city", city);
        params.put("taluka", taluka);
        params.put("district", district);

        String response = Network.connect("http://" + Network.IP + "/showroom_registration.php", params);

        if (response != null) {
            if (response.trim().equals("approval is pending")) {
                Toast.makeText(getApplicationContext(), "Approval is pending. You cannot log in yet.", Toast.LENGTH_LONG).show();
            } else if (response.trim().equals("0")) {
                Toast.makeText(getApplicationContext(), "Registration failed. Please check your information.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Registration successful. You can now log in.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(showroom_registration.this, showroom_login.class);
                startActivity(intent);
                finish(); // Prevents going back to registration screen with back button
            }
        } else {
            Toast.makeText(getApplicationContext(), "Error connecting to server. Please try again later.", Toast.LENGTH_LONG).show();
        }
    }

    // Helper method to validate email format
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}