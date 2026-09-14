package com.example.bustrackingrfid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class DriverLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
        EditText user=findViewById(R.id.editDriverLoginUsername);

        EditText pass=findViewById(R.id.editDriverLoginPassword);

        Button login=findViewById(R.id.btnDriverLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText().toString().isEmpty() && pass.getText().toString().isEmpty()){
                    Toast.makeText(DriverLogin.this, "Please enter the Username and password", Toast.LENGTH_SHORT).show();
                }else{
                    HashMap<String ,String> p=new HashMap<String, String>();
                    p.put("driver_mobile",user.getText().toString().trim());
                    p.put("password",pass.getText().toString().trim());

                    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String rs=Network.connect("http://"+Network.IP+"/driver_login.php",p);
                     
                    String rss=rs.trim();
                    if (rss.equals("0")){
                        Toast.makeText(DriverLogin.this, "Incorrect details", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent=new Intent(DriverLogin.this,DriverModule.class);
                        intent.putExtra("id",rs.trim());
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }
}