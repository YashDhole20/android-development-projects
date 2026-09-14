package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ParentRegistartion extends AppCompatActivity {

    private EditText parentName,studentid,mobile,password;
    private Button register;
    private TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_registartion);

        parentName=findViewById(R.id.editParentName);
        studentid=findViewById(R.id.editParentChildID);
        mobile=findViewById(R.id.editParentMobile);
        password=findViewById(R.id.editParentPassword);

        register=findViewById(R.id.btnParentRegistration);
        back=findViewById(R.id.textGotoSignin);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ParentLogin.class);
                startActivity(intent);
                finish();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> param=new HashMap<String, String>();

                if(TextUtils.isEmpty(parentName.getText().toString())){
                    parentName.setError("Enter Parent Name");
                    parentName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(studentid.getText().toString())){
                    studentid.setError("Enter the Student Id");
                    studentid.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mobile.getText().toString())){
                    mobile.setError("Enter the Mobile No");
                    mobile.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Enter Password");
                    password.requestFocus();
                    return;
                }

                param.put("parent_name",parentName.getText().toString());
                param.put("student_id",studentid.getText().toString());
                param.put("mobile", mobile.getText().toString());
                param.put("password",password.getText().toString());

                String rs=Network.connect("http://"+Network.IP+"/parentregistration.php",param);

                if (rs.equals("0")){
                    Toast.makeText(ParentRegistartion.this, "Registration Fail..", Toast.LENGTH_SHORT).show();
                } else if (!rs.equals("0")) {
                    Toast.makeText(ParentRegistartion.this, "Registration Successfully..", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ParentLogin.class));
                    finish();
                }
            }
        });



    }
}