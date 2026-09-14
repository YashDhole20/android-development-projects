package com.apti.smartparking;

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

public class LoginActivity extends AppCompatActivity {
    EditText editmobile,editpassword;
    Button btnlogin;
    TextView txtregister;
    static String idd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editmobile=(EditText)findViewById(R.id.editmobile);
        editpassword=(EditText)findViewById(R.id.editpassword);
        txtregister=(TextView)findViewById(R.id.txtregister);
        btnlogin=(Button)findViewById(R.id.btnlogin);

        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent);
            }
        });



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        if(editmobile.getText().toString().isEmpty() || editpassword.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
                        }else {

                            HashMap<String, String> param = new HashMap<String, String>();
                            param.put("mobile", editmobile.getText().toString());
                            param.put("password", editpassword.getText().toString());

                            StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(sb);
                            String id = Network.connect("http://" + Network.IP + "/login.php", param);
                            id = id.trim();

                            idd=id;
                            if (id.equals("0")) {

                                Toast.makeText(getApplicationContext(), "Login unsuccessfull", Toast.LENGTH_LONG).show();
                            } else  {
                                Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                                startActivity(intent);
                                finish();
                            }
                        }


                }

        });
    }
}