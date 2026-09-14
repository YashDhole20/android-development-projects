package com.example.ecochargehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecochargehub.R;

public class Admin extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button loginButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        emailEditText=(EditText)findViewById(R.id.emailEditText);
        passwordEditText=(EditText)findViewById(R.id.passwordEditText);
        loginButton=(Button) findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailEditText.getText().toString().equals("admin") && passwordEditText.getText().toString().equals("admin123")){

                    Toast.makeText(Admin.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(getApplicationContext(),admin_section.class);
                    startActivity(in);
                }else

                    Toast.makeText(Admin.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();

            }
        });
    }
}