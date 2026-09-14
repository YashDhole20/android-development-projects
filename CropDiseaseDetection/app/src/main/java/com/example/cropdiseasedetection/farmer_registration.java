package com.example.cropdiseasedetection;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

    public class farmer_registration extends AppCompatActivity {
        TextInputEditText name_et, address_et, mobile_et, usrname_et, password_et;

        Button btnsignup;
        TextView txtsignin;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_farmer_registration);

            name_et = (TextInputEditText) findViewById(R.id.name);
            address_et = (TextInputEditText) findViewById(R.id.address);
            mobile_et = (TextInputEditText) findViewById(R.id.mobile);
            usrname_et = (TextInputEditText) findViewById(R.id.usrname_et);
            password_et = (TextInputEditText) findViewById(R.id.password_et);
            btnsignup = findViewById(R.id.btnsignup);
            txtsignin = (TextView) findViewById(R.id.textsignin);
//
//
//            txtsignin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent in = new Intent(getApplicationContext(), farmer_login.class);
//                    startActivity(in);
//                }
//            });
//
            btnsignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerUser();
                }
            });
        }

        private void registerUser() {
            final String name = name_et.getText().toString().trim();
            final String address = address_et.getText().toString().trim();
            final String email = usrname_et.getText().toString().trim();
            final String password = password_et.getText().toString().trim();
            final String mobile = mobile_et.getText().toString().trim();


            if (TextUtils.isEmpty(name)) {
                name_et.setError("Please enter name");
                name_et.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(address)) {
                address_et.setError("Please enter address");
                address_et.requestFocus();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                usrname_et.setError("Enter a valid email");
                usrname_et.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(mobile)) {
                mobile_et.setError("Enter a Mobile No");
                mobile_et.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                password_et.setError("Enter a password");
                password_et.requestFocus();
                return;
            }

            HashMap<String, String> params = new HashMap<>();

            params.put("name", name);
            params.put("address", address);
            params.put("mobile", mobile);
            params.put("email", email);
            params.put("password", password);


            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //returing the response
            String id = Network.connect("http://" + Network.IP + "/farmer_registration.php", params);
            //System.out.println("resp" + id);
            if (id.trim() == "0") {
                Toast.makeText(getApplicationContext(), "Wrong Info", Toast.LENGTH_LONG).show();

            } else {
                Intent in = new Intent(getApplicationContext(), farmer_login.class);
                startActivity(in);
            }
        }
    }
