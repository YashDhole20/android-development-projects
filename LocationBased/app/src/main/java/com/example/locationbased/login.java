package com.example.locationbased;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class login extends AppCompatActivity {

    private EditText username,password;
    private Button loginBtn;
    private TextView gotoSignup,forgetPassword;
    static String idd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.editTxtLoginUserNm);
        password=(EditText)findViewById(R.id.editTxtLoginPassword );
        loginBtn=(Button) findViewById(R.id.btnLogin);
        gotoSignup=findViewById(R.id.txtGotoSignup);
        forgetPassword=findViewById(R.id.txtForgetPass);
        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> param = new HashMap<String, String>();
//        String [] se=text.getText().toString().split(",");
                param.put("uname",username.getText().toString().trim());
                param.put("password",password.getText().toString().trim());
                String rs = Network.connect("http://" + Network.IP + "/login.php",
                        param);

                System.out.println("ff"+rs);
                if(rs.trim().equals("0")){
                    Toast.makeText(getApplicationContext(),"Login Not Successfully..",Toast.LENGTH_LONG).show();
                    Intent in=new Intent(getApplicationContext(),Login.class);
                    startActivity(in);

                }else{
                    Toast.makeText(getApplicationContext(),"Login Successfully..",Toast.LENGTH_LONG).show();

                    Intent in=new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(in);

                }
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, MainActivity_forgetPassword.class);
                startActivity(intent);
            }
        });
    }

}