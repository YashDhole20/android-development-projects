package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AdminLogin extends AppCompatActivity {
    EditText editmobile,editpassword;
    Button btnlogin;
    Button txtregister;
    static String idd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        editmobile=(EditText)findViewById(R.id.et_username);
        editpassword=(EditText)findViewById(R.id.et_password);
        btnlogin=(Button) findViewById(R.id.bt_submit);
        txtregister=(Button)findViewById(R.id.bt_login_register);

        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AdminRegister.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editmobile.getText().toString().trim().equals("admin") && editpassword.getText().toString().trim().equals("123")){
                    Toast.makeText(getApplicationContext(),"Login Successfully..",Toast.LENGTH_LONG).show();
//
                    Intent in=new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(in);
                }else {

                    Toast.makeText(getApplicationContext(),"Login Not Successfully..",Toast.LENGTH_LONG).show();
//
                    Intent in=new Intent(getApplicationContext(),AdminLogin.class);
                    startActivity(in);
                }
                //                HashMap<String, String> param = new HashMap<String, String>();
////        String [] se=text.getText().toString().split(",");
//                param.put("uname",editmobile.getText().toString().trim());
//                param.put("pass",editpassword.getText().toString().trim());
//
//                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                        .detectAll()
//                        .penaltyLog()
//                        .penaltyFlashScreen()
//                        .penaltyDeath()
//                        .build());
//                String rs = Network.connect("http://" + Network.IP + "/adminlogin.php",
//                        param);
//
//                System.out.println("ff"+rs);
//                if(rs.trim().equals("0")){
//                    Toast.makeText(getApplicationContext(),"Login Not Successfully..",Toast.LENGTH_LONG).show();
//                    Intent in=new Intent(getApplicationContext(),Login.class);
//                    startActivity(in);
//
//                }else{
//                    Toast.makeText(getApplicationContext(),"Login Successfully..",Toast.LENGTH_LONG).show();
//
//                    Intent in=new Intent(getApplicationContext(),Dashboard.class);
//                    startActivity(in);
//
//                }
            }
        });
    }
}