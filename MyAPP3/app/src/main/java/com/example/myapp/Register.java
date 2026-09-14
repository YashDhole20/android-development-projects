package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Register extends AppCompatActivity {
   private EditText mobileEdit,passwordEdit,usernmEdit,fullnmEdit,emailEdit,confirmPasswordEdit;
    private Button btnreg;
    private TextView gotoLogin;
    ListView ltcomp;
    String[] values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mobileEdit=findViewById(R.id.signuppMobileNo);
        passwordEdit=findViewById(R.id.signupPassword);
        usernmEdit=findViewById(R.id.signupUserNm);
        fullnmEdit=findViewById(R.id.signupFullNm);
        btnreg=findViewById(R.id.signupBtnRegister);
        emailEdit=findViewById(R.id.signupEmail);
        confirmPasswordEdit=findViewById(R.id.signupConfirmPassword);
        gotoLogin=findViewById(R.id.signupGotoLogin);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> param = new HashMap<String, String>();
                if (TextUtils.isEmpty(fullnmEdit.getText().toString().trim())) {
                    fullnmEdit.setError("Please enter name");
                    fullnmEdit.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(usernmEdit.getText().toString().trim())) {
                    usernmEdit.setError("Please enter username");
                    usernmEdit.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(emailEdit.getText().toString().trim())) {
                    emailEdit.setError("Please enter email");
                    emailEdit.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(confirmPasswordEdit.getText().toString().trim())) {
                    confirmPasswordEdit.setError("Please enter confirm password");
                    confirmPasswordEdit.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mobileEdit.getText().toString().trim())) {
                    mobileEdit.setError("Please enter mobile number");
                    mobileEdit.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(passwordEdit.getText().toString().trim())) {
                    passwordEdit.setError("Please enter password");
                    passwordEdit.requestFocus();
                    return;
                }

                if (!TextUtils.equals(passwordEdit.getText().toString().trim(),confirmPasswordEdit.getText().toString().trim())) {
                    confirmPasswordEdit.setError("Confirm Password not match");
                    confirmPasswordEdit.requestFocus();
                    return;
                }
//        String [] se=text.getText().toString().split(",");
                param.put("name",fullnmEdit.getText().toString());
                param.put("uname",usernmEdit.getText().toString());
                param.put("email",emailEdit.getText().toString());
                param.put("mobile",mobileEdit.getText().toString());
                param.put("pass",passwordEdit.getText().toString());

//                StrictMode.ThreadPolicy policy =
//                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(policy);
                String rs = Network.connect("http://" + Network.IP + "/register.php",
                        param);




             //   System.out.println("ff"+rs);
                Toast.makeText(getApplicationContext(),"res"+rs,Toast.LENGTH_LONG).show();
                if(rs=="0"){
                    Toast.makeText(getApplicationContext(),"Signup Not Successfully..",Toast.LENGTH_LONG).show();
                    Intent in=new Intent(getApplicationContext(),Register.class);
                    startActivity(in);
                }else if(rs!="0"){
                    Toast.makeText(getApplicationContext(),"Signup Successfully..",Toast.LENGTH_LONG).show();
                    Intent in=new Intent(getApplicationContext(),Login.class);
                    startActivity(in);
                }
            }
        });
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

    }
}