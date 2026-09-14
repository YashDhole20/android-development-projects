package com.myandroidproject.ecochargehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class user_registration extends AppCompatActivity {
    EditText emailEditText, passwordEditText, mobileEditText, cityEditText;

    Button loginButton;
    TextView registerText;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        emailEditText =   findViewById(R.id.emailEditText);
        passwordEditText =   findViewById(R.id.passwordEditText);
        mobileEditText =   findViewById(R.id.mobileEditText);
        cityEditText =  findViewById(R.id.cityEditText);
        loginButton = findViewById(R.id.loginButton);
        registerText =   findViewById(R.id.registerText);


        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), user_login.class);
                startActivity(in);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String mobile = mobileEditText.getText().toString().trim();
        final String city = cityEditText.getText().toString().trim();



        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Please enter email");
            emailEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Please password");
            passwordEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            mobileEditText.setError("Enter a mobile no");
            mobileEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(city)) {
            cityEditText.setError("Enter a city");
            cityEditText.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();

        params.put("email", email);
        params.put("password", password);
        params.put("mobile", mobile);
        params.put("city", city);



        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //returing the response
        String id = Net.connect("http://" + Net.IP + "/user_registration.php", params);
        //System.out.println("resp" + id);
        if (id.trim() == "0") {
            Toast.makeText(getApplicationContext(), "Wrong Info", Toast.LENGTH_LONG).show();

        } else {
            Intent in = new Intent(getApplicationContext(), user_login.class);
            startActivity(in);
        }
    }
}
