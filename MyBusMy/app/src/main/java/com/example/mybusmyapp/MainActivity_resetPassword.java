package com.example.mybusmyapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_resetPassword extends AppCompatActivity    {

    private EditText password,confirmPass;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reset_password);

        password=findViewById(R.id.editTxtPassword);
        confirmPass=findViewById(R.id.editTxtConfirmPassword);

        submit=findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (password.getText().toString().equals(confirmPass.getText().toString()) && !password.getText().toString().isEmpty()) {
//                    Intent intent=new Intent(MainActivity_resetPassword.this,MainActivity_OTP.class);
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(MainActivity_resetPassword.this, "Password is not match..", Toast.LENGTH_SHORT).show();
//                }
                String pass=password.getText().toString();
                String conPass=confirmPass.getText().toString();
                if (TextUtils.isEmpty(pass)){
                    password.setError("Please enter password");
                    password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(conPass)){
                    confirmPass.setError("Please enter username");
                    confirmPass.requestFocus();
                    return;
                }
                if (!TextUtils.equals(pass,conPass)) {
                    confirmPass.setError("Confirm Password not match");
                    confirmPass.requestFocus();
                    return;
                }

                startActivity(new Intent(MainActivity_resetPassword.this, Dashboard.class));
                Toast.makeText(MainActivity_resetPassword.this, "Passoword update successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

}