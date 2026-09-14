package com.example.busapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class StudentLogin extends AppCompatActivity {

    private EditText username,password;
    private Button loginBtn;
    private TextView signupTxt,forgetTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        username=findViewById(R.id.editStudLoginUserNm);
        password=findViewById(R.id.editStudLoginPassword);
        loginBtn=findViewById(R.id.btnStudentLogin);
        forgetTxt=findViewById(R.id.textStudLoginForgotPass);
        signupTxt=findViewById(R.id.textStudentGotoSignup);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.btnStudentLogin:
                        if (username.getText().toString().isEmpty() ||password.getText().toString().isEmpty()){
                            Toast.makeText(StudentLogin.this, "Empty not allowed", Toast.LENGTH_SHORT).show();
                        }
                        else{
//                            HashMap<String,String> param=new HashMap<String, String>();
//                            param.put("email",username.getText().toString());
//                            param.put("password",password.getText().toString());
//
//                            StrictMode.ThreadPolicy sb=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                            StrictMode.setThreadPolicy(sb);
//                            String id= Network.connect("http:///"+Network.IP+"/login.php",param);
//                            id=id.trim();
//                            id=id;
//
//                            if (id.equals("0")){
//                                Toast.makeText(StudentLogin.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
//                            }
//                            else{
//                                Toast.makeText(StudentLogin.this, "Login Successfully!!", Toast.LENGTH_SHORT).show();
//                                Intent intent=new Intent(StudentLogin.this,####);
//                                startActivity(intent);
//                                finish();
//                            }


                            Toast.makeText(StudentLogin.this, "Login Successfully!!", Toast.LENGTH_SHORT).show();
                        }
                }
//            }
        });

        signupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentLogin.this,StudentSignup.class);
                startActivity(intent);
            }
        });
    }
}