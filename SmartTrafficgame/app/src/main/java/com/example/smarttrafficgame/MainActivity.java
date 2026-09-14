package com.example.smarttrafficgame;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttrafficgame.R;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etAddress , etEmail ,etMobileNo;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        etAddress  = findViewById(R.id.Address);
        etEmail    = findViewById(R.id.email);
        etMobileNo = findViewById(R.id.MobileNO);
        btnRegister = findViewById(R.id.Signupbutton);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (TextUtils.isEmpty(etUsername.getText().toString())) {
                        etUsername.setError("Enter your Name");
                        etUsername.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(etPassword.getText().toString())){
                        etPassword.setError("Enter Your Password");
                        etPassword.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(etAddress.getText().toString())){
                        etAddress.setError("Enter  Address");
                        etAddress.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(etEmail.getText().toString())){
                        etEmail.setError("Enter Your  Email");
                        etEmail.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(etMobileNo.getText().toString())){
                        etMobileNo.setError("Enter Mobile No: ");
                        etMobileNo.requestFocus();
                        return;
                    }

                    HashMap<String,String> param=new HashMap<String, String>();

                    param.put("username",etUsername.getText().toString());
                    param.put("Email",etEmail.getText().toString());
                    param.put("Password",etPassword.getText().toString());
                    param.put("MobileNo",etMobileNo.getText().toString());
                    param.put("Address",etAddress.getText().toString());


                String rs = Network.connect("http://" + Network.IP + "/register.php", param);
                System.out.println("resp" + rs);

                if (rs.equals("0")) {
                    Toast.makeText( getApplicationContext(), "Wrong Info", Toast.LENGTH_LONG).show();

                } else if (!rs.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(getApplicationContext(), Loginpage1.class);
                    startActivity(in);
                }

                }
            });
            }

}