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

public class AdminRegister extends AppCompatActivity {
    EditText editmobile,editpassword,etusername,etname;
    Button btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        editmobile=findViewById(R.id.editmobnumber);
        editpassword=findViewById(R.id.editpass);
        etusername=findViewById(R.id.edit_reg_uname);
        etname=findViewById(R.id.editname);
        btnreg=findViewById(R.id.btnregister);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> param = new HashMap<String, String>();
//        String [] se=text.getText().toString().split(",");
                param.put("name",etname.getText().toString().trim());
                param.put("uname",etusername.getText().toString().trim());
                param.put("pass",editpassword.getText().toString().trim());
                param.put("mobile",editmobile.getText().toString().trim());

                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .penaltyFlashScreen()
                        .penaltyDeath()
                        .build());
                String rs = Network.connect("http://" + Network.IP + "/adminregister.php",
                        param);


                System.out.println("ff"+rs);
                Toast.makeText(getApplicationContext(),"res"+rs,Toast.LENGTH_LONG).show();
                if(rs.trim().equals("0")){
                    Toast.makeText(getApplicationContext(),"Signup Not Successfully..",Toast.LENGTH_LONG).show();
                    Intent in=new Intent(getApplicationContext(),Register.class);
                    startActivity(in);

                }else{
                    Toast.makeText(getApplicationContext(),"Signup Successfully..",Toast.LENGTH_LONG).show();

                    Intent in=new Intent(getApplicationContext(),Login.class);
                    startActivity(in);

                }
            }
        });
    }
}