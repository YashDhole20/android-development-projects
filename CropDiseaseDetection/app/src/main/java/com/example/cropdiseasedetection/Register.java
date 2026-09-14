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

public class Register extends AppCompatActivity {
    TextInputEditText agrname, agraddress, agrmobile, agremail, agrpassword;
    Button btnagrsignup;
    TextView txtagrsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        agrname = findViewById(R.id.agrname);
        agraddress = findViewById(R.id.agraddress);
        agrmobile = findViewById(R.id.agrmobile);
        agremail = findViewById(R.id.agremail);
        agrpassword = findViewById(R.id.agrpassword);
        btnagrsignup = findViewById(R.id.btnagrsignup);
        txtagrsignin = findViewById(R.id.txtagrsignin);

        txtagrsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), agriculture_login.class);
                startActivity(in);
            }
        });

        btnagrsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String name = agrname.getText() != null ? agrname.getText().toString().trim() : "";
        final String address = agraddress.getText() != null ? agraddress.getText().toString().trim() : "";
        final String email = agremail.getText() != null ? agremail.getText().toString().trim() : "";
        final String password = agrpassword.getText() != null ? agrpassword.getText().toString().trim() : "";
        final String mobile = agrmobile.getText() != null ? agrmobile.getText().toString().trim() : "";

        if (TextUtils.isEmpty(name)) {
            agrname.setError("Please enter name");
            agrname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            agraddress.setError("Please enter address");
            agraddress.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            agremail.setError("Enter a valid email");
            agremail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            agrmobile.setError("Enter a Mobile No");
            agrmobile.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            agrpassword.setError("Enter a password");
            agrpassword.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("address", address);
        params.put("mobile", mobile);
        params.put("password", password);
        params.put("email", email);
        StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(sb);
        String id = Network.connect("http://" + Network.IP + "/agriculture_registration.php", params);

        if (id != null) {
            if (id.trim().equals("approval is pending")) {
                Toast.makeText(getApplicationContext(), "Approval is pending. You cannot log in yet.", Toast.LENGTH_LONG).show();
            } else if (id.trim().equals("0")) {
                Toast.makeText(getApplicationContext(), "Wrong Info", Toast.LENGTH_LONG).show();
            } else {
                Intent in = new Intent(getApplicationContext(), agriculture_login.class);
                startActivity(in);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Error connecting to server", Toast.LENGTH_LONG).show();
        }
    }
}



//package com.example.cropdiseasedetection;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.textfield.TextInputEditText;
//
//import java.util.HashMap;
//
//public class Register extends AppCompatActivity {
//    TextInputEditText agrname,agraddress,agrmobile,agremail,agrpassword;
//    Button btnagrsignup;
//    TextView txtagrsignin;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        agrname=(TextInputEditText) findViewById(R.id.agrname);
//        agraddress=(TextInputEditText) findViewById(R.id.agraddress);
//        agrmobile=(TextInputEditText) findViewById(R.id.agrmobile);
//        agremail=(TextInputEditText) findViewById(R.id.agremail);
//        agrpassword=(TextInputEditText) findViewById(R.id.agrpassword);
//        btnagrsignup=  findViewById(R.id.btnagrsignup);
//        txtagrsignin=(TextView) findViewById(R.id.txtagrsignin);
//
//        txtagrsignin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in=new Intent(getApplicationContext(),agriculture_login .class);
//                startActivity(in);
//            }
//        });
//
//        btnagrsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                registerUser();
//            }
//        });
//    }
//    private void registerUser() {
//        final String name = agrname.getText().toString().trim();
//        final String address = agraddress.getText().toString().trim();
//        final String email = agremail.getText().toString().trim();
//        final String password = agrpassword.getText().toString().trim();
//        final String mobile = agrmobile.getText().toString().trim();
//
//
//        if (TextUtils.isEmpty(name)) {
//            agrname.setError("Please enter name");
//            agrname.requestFocus();
//            return;
//        }
//        if (TextUtils.isEmpty(address)) {
//            agraddress.setError("Please enter address");
//            agraddress.requestFocus();
//            return;
//        }
//
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            agremail.setError("Enter a valid email");
//            agremail.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(mobile)) {
//            agrmobile.setError("Enter a Mobile No");
//            agrmobile.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(password)) {
//            agrpassword.setError("Enter a password");
//            agrpassword.requestFocus();
//            return;
//        }
//
//        HashMap<String, String> params = new HashMap<>();
//
//        params.put("name", name);
//        params.put("address", address);
//        params.put("mobile", mobile);
//        params.put("password", password);
//        params.put("email", email);
//
//        String id = Network.connect("http://" + Network.IP + "/agriculture_registration.php", params);
//        System.out.println("resp" + id);
//        if (id.trim() == "0") {
//            Toast.makeText(getApplicationContext(), "Wrong Info", Toast.LENGTH_LONG).show();
//
//        } else {
//            Intent in = new Intent(getApplicationContext(),agriculture_login.class);
//            startActivity(in);
//        }
//    }
//}