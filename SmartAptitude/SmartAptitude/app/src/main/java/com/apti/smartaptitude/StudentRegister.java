package com.apti.smartaptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class StudentRegister extends AppCompatActivity {
EditText etfname,etlname,etemail,etpass,etmob;
Button btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        etfname=findViewById(R.id.et_reg_fname);
        etlname=findViewById(R.id.et_reg_lname);
        etemail=findViewById(R.id.et_reg_email);
        etpass=findViewById(R.id.et_reg_pass);
        etmob=findViewById(R.id.et_reg_mob);
        btnreg=findViewById(R.id.signupBtnRegister);
        TextView login=findViewById(R.id.signupGotoLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),StudentLogin.class));
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                HashMap<String, String> param = new HashMap<String, String>();
//        String [] se=text.getText().toString().split(",");
                param.put("fname",etfname.getText().toString().trim());
                param.put("lname",etlname.getText().toString().trim());
                param.put("mob",etmob.getText().toString().trim());
                param.put("email",etemail.getText().toString().trim());
                param.put("password",etpass.getText().toString().trim());

                String rs = Network.connect("http://" + Network.IP + "/register.php",
                        param);

                if(rs.trim().equals("1")){
                    Toast.makeText(getApplicationContext(),"Register Successfully..",Toast.LENGTH_LONG).show();
                    Intent in=new Intent(getApplicationContext(),StudentLogin.class);
                    startActivity(in);
                }else {
                    Toast.makeText(getApplicationContext(),"Register Not Successfully..",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}