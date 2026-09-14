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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParentLogin extends AppCompatActivity {

    private EditText username,password;
    private Button loginBtn;
    private TextView gotoSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        username=findViewById(R.id.editParentLoginUsername);
        password=findViewById(R.id.editParentLoginPassword);
        loginBtn=findViewById(R.id.btnParentLogin);
        gotoSignup=findViewById(R.id.textGotoSignup);


        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ParentRegistartion.class));
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> param = new HashMap<String, String>();

                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("Enter the Mobile number");
                    username.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Enter the Password");
                    password.requestFocus();
                    return;
                }

                param.put("mobile", username.getText().toString());
                param.put("password", password.getText().toString());

                String rs = Network.connect("http://" + Network.IP + "/parentlogin.php", param);

                List<String> parentDetails = Arrays.asList(rs.trim().split("<br>"));

                 System.out.println(rs);
                System.out.println(parentDetails);

                if (parentDetails.get(0).equals("0")) {
                    Toast.makeText(ParentLogin.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                }else if(rs != "0") {
                    Toast.makeText(ParentLogin.this, "Login Successfully...", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), ParentModule.class);
                    intent.putExtra("parent_name", parentDetails.get(0));
                    intent.putExtra("studentid",parentDetails.get(1));
                    startActivity(intent);
                    finish();
                }


            }
        });



    }
}