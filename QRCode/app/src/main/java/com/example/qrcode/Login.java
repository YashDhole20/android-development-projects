package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    private EditText username,pass;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.usernameEdit);
        pass=findViewById(R.id.passwordEdit);
        login=findViewById(R.id.loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("user") && pass.getText().toString().equals("user")){
                    startActivity(new Intent(Login.this,MainActivity.class));
                }else{
                    Toast.makeText(Login.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}