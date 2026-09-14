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

public class ParentLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        EditText user=findViewById(R.id.editParentLoginUsername);
        EditText pass=findViewById(R.id.editParentLoginPassword);


        Button btn_login=findViewById(R.id.btnParentLogin);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText().toString().isEmpty() && pass.getText().toString().isEmpty()){
                    Toast.makeText(ParentLogin.this, "Please enter the Username and password", Toast.LENGTH_SHORT).show();
                }else{
                    HashMap<String ,String> p=new HashMap<String, String>();
                    p.put("parent_mobile",user.getText().toString().trim());
                    p.put("rfid",pass.getText().toString().trim());

                    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String rs=Network.connect("http://"+Network.IP+"/parent_login.php",p);
                    String rss=rs.trim();
                    if (rss.equals("0")){
                        Toast.makeText(ParentLogin.this, "Incorrect details", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent=new Intent(ParentLogin.this,PresentStudentForParent.class);
                        intent.putExtra("rfid",pass.getText().toString().trim());
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }
}