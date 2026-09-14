package com.example.busapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    private EditText username,password;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username=findViewById(R.id.editAdminLoginUsername);
        password=findViewById(R.id.editAdminLoginPassword);
        loginBtn=findViewById(R.id.btnAdminLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Intent intent =new Intent(AdminLogin.this, AdminPanel.class );
                    startActivity(intent);
                    Toast.makeText(AdminLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AdminLogin.this, "Login Failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}