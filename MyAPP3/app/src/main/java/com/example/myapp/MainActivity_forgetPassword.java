package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity_forgetPassword extends AppCompatActivity  {

     EditText userNmForgetPassword;
     Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forget_password);

        userNmForgetPassword=findViewById(R.id.forgetPassTxtUsernm);
        nextBtn=findViewById(R.id.btnNext);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=userNmForgetPassword.getText().toString();
                if (TextUtils.isEmpty(username)){
                    userNmForgetPassword.setError("Please enter username");
                    userNmForgetPassword.requestFocus();
                    return;
                }
//                HashMap<String, String> param = new HashMap<String, String>();
//                param.put("username",username);
//                String rs = Network.connect("http://" + Network.IP + "/login.php",param);
//
//                System.out.println("ff"+rs);

                Intent intent=new Intent(MainActivity_forgetPassword.this,MainActivity_OTP.class);
                startActivity(intent);
            }
        });


    }


}
