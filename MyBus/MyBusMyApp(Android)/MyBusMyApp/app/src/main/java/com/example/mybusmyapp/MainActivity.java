package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;

    TextInputLayout emailWrapper, passwordWrapper;
    Button login;
    private TextInputEditText emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailWrapper = (TextInputLayout) findViewById(R.id.text_input_layout_email);
        passwordWrapper = (TextInputLayout) findViewById(R.id.text_input_layout_password);
        login = (Button) findViewById(R.id.btndriverLogin);
        emailInput = (TextInputEditText) findViewById(R.id.usrname_et);
        passwordInput = (TextInputEditText) findViewById(R.id.password_et);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailInput.getText().toString().equals("driver") && passwordInput.getText().toString().equals("driver123")){

                    Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }else

                    Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
                }
        });
    }
}