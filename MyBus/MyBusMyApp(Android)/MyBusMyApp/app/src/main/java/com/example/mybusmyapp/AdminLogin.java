package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdminLogin extends AppCompatActivity {

    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        login = (Button) findViewById(R.id.loginbtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin123")){

                    Toast.makeText(AdminLogin.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(getApplicationContext(),dashboard.class);
                    startActivity(in);
                }else

                    Toast.makeText(AdminLogin.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}