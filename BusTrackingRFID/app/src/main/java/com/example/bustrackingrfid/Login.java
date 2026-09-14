package com.example.bustrackingrfid;

//import androidx.appcompat.app.AppCompatActivity;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText editmobile,editpassword;
    Button btnlogin;
    Button txtregister;
    static String idd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editmobile=(EditText)findViewById(R.id.et_username);
        editpassword=(EditText)findViewById(R.id.et_password);
        btnlogin=(Button) findViewById(R.id.bt_submit);


//        txtregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Register.class);
//                startActivity(intent);
//            }
//        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editmobile.getText().toString().equals("admin") && editpassword.getText().toString().equals("123")){
                Intent intent = new Intent(getApplicationContext(),AdminPanel.class);
                startActivity(intent);
                finish();
            }else{
                    Toast.makeText(Login.this, "Login Failled", Toast.LENGTH_SHORT).show();
            }
        }
    });
}
}