package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ForgetPassword extends AppCompatActivity {
    
    
    private EditText forgetUsername;
    private Button nextBtn,backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        forgetUsername=findViewById(R.id.editForgetUsername);
        nextBtn=findViewById(R.id.btnForgetNext);
        backBtn=findViewById(R.id.btnForgetBack);


        int id=getIntent().getIntExtra("id",11);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id==0) {
                    if (forgetUsername.getText().toString().equals("admin")) {
                        startActivity(new Intent(getApplicationContext(), MainActivity_OTP.class));
                    } else {
                        Toast.makeText(ForgetPassword.this, "Invalid Admin Username", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (id==1){
                    HashMap<String,String> param=new HashMap<String, String>();
                    param.put("studentid",forgetUsername.getText().toString().trim());
                    String rs=Network.connect("http://"+Network.IP+"/forgetPassword.php",param);
                    System.out.println(rs);
                    if (rs.equals("0")){
                        Toast.makeText(ForgetPassword.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    } else if (!rs.equals("0")) {
                        startActivity(new Intent(getApplicationContext(),MainActivity_OTP.class));
                    }
                }
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id==0){
                    startActivity(new Intent(getApplicationContext(),AdminLogin.class));

                } else if (id==1) {
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
            }
        });

    }
}