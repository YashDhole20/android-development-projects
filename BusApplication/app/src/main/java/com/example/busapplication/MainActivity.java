package com.example.busapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private TextInputLayout emailWrapper,passwordWrapper;
    private Button loginBtn;
    private TextInputEditText emailInput,passwordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailWrapper=findViewById(R.id.text_input_layout_email);
        passwordWrapper=findViewById(R.id.text_input_layout_password);
        loginBtn=findViewById(R.id.btndriverLogin);
        emailInput=findViewById(R.id.editDriverLoginUsernm);
        passwordInput=findViewById(R.id.editDriverLoginPassword);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailInput.getText().toString().equals("abc") && passwordInput.getText().toString().equals("abc")){
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}