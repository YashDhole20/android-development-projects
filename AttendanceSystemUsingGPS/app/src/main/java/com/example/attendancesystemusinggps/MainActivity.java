package com.example.attendancesystemusinggps;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

//import com.google.android.material.textfield.TextInputLayout;
//import android.support.v7.widget.AppCompatSpinner;

public class MainActivity extends AppCompatActivity {

    TextInputLayout inputname,inputenroll,inputpass;
    EditText editname,editenroll,editpass;
    Button btnregister;
    TextView txtviewlogin;
    AppCompatSpinner dept,class1;
    String name,enrollno,pass,dept1,classs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputname=(TextInputLayout)findViewById(R.id.inputname);
        inputenroll=(TextInputLayout)findViewById(R.id.inputenroll);
        inputpass=(TextInputLayout)findViewById(R.id.inputpass);
        editname=(EditText)findViewById(R.id.editname);
        editenroll=(EditText)findViewById(R.id.editenroll);
        editpass=(EditText)findViewById(R.id.editpass);
        dept=(AppCompatSpinner)findViewById(R.id.dept);
        class1=(AppCompatSpinner)findViewById(R.id.class1);
        btnregister=(Button)findViewById(R.id.btnregister);
        txtviewlogin=(TextView)findViewById(R.id.txtviewlogin);

        name=editname.getText().toString().trim();
        enrollno=editenroll.getText().toString().trim();
        pass=editpass.getText().toString().trim();
        dept1=dept.getSelectedItem().toString().trim();
        classs=class1.getSelectedItem().toString().trim();


        txtviewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    registerForm();

                HashMap<String, String> param = new HashMap<String, String>();
                param.put("name", editname.getText().toString());
                param.put("enrollno", editenroll.getText().toString());
                param.put("dept", dept.getSelectedItem().toString());
                param.put("classs",class1.getSelectedItem().toString());
                param.put("password", editpass.getText().toString());

                StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(sb);
                String id = Network.connect("http://" + Network.IP + "/user_register.php", param);
                id = id.trim();
                if (id.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Registration unsuccessfully", Toast.LENGTH_LONG).show();
                } else if (!id.equals("1")) {
                    Toast.makeText(getApplicationContext(), "Registration successfull", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);

                }
                    
            }
        });

    }

    private boolean registerForm() {
        if (!validateName()) {
            return true;
        } if (!validatePassword()) {
            return true;
        }
        return false;

    }


    private boolean validateName()
    {
        if (editname.getText().toString().trim().isEmpty()) {
            inputname.setErrorEnabled(true);
            requestFocus(editname);
            return false;
        } else {
            inputname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword()
    {
        if (editpass.getText().toString().trim().isEmpty()) {
            inputpass.setErrorEnabled(true);
            requestFocus(editpass);
            return false;
        } else {
            inputpass.setErrorEnabled(false);
        }

        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
