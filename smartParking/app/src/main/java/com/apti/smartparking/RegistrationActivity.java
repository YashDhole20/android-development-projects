package com.apti.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText editname,editcar,editcarnumber,editnumber,editpass;
    Button btnregister;
    String name,car,carnumber,number,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editname=(EditText)findViewById(R.id.editname);
        editcar=(EditText)findViewById(R.id.editcar);
        editcarnumber=(EditText)findViewById(R.id.editcarnumber);
        editnumber=(EditText)findViewById(R.id.editnumber);
        editpass=(EditText)findViewById(R.id.editpass);
        btnregister=(Button)findViewById(R.id.btnregister);

        name = editname.getText().toString();
        car = editcar.getText().toString();
        carnumber = editcarnumber.getText().toString();
        number = editnumber.getText().toString();
        password = editpass.getText().toString();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean y=     checkNull();

//                Toast.makeText(getApplicationContext(),"OUT"+y,Toast.LENGTH_LONG).show();

                        if( y==false){
                            Toast.makeText(getApplicationContext(),"Empty Not Allowed...",Toast.LENGTH_LONG).show();
                        }else{

                            HashMap<String, String> param = new HashMap<String, String>();
                            param.put("name", editname.getText().toString());
                            param.put("car", editcar.getText().toString());
                            param.put("carnumber", editcarnumber.getText().toString());
                            param.put("mobile", editnumber.getText().toString());
                            param.put("password", editpass.getText().toString());

                            StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(sb);
                            String id = Network.connect("http://" + Network.IP + "/register.php", param);

                            id = id.trim();
                            if (id.equals("0")) {
                                Toast.makeText(getApplicationContext(), "Registration unsuccessfull", Toast.LENGTH_LONG).show();
                            } else if (!id.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Registration successfull", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                }

        });
    }
    private boolean checkNull()
    {
        boolean isTrue = false;

        if (editcar.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter valid name", Toast.LENGTH_LONG).show();
            return isTrue;
        } else if (editcarnumber.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Enter valid car name", Toast.LENGTH_LONG).show();
            return isTrue;
        }else if (editname.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Enter valid car number", Toast.LENGTH_LONG).show();
            return isTrue;
        }else if (editnumber.getText().toString().isEmpty() )
        {
            Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
            return isTrue;
        }
        else if (isValidMobile(editnumber.getText().toString())==false)
        {
            Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
            editnumber.setError("Invalid Mobile Number..");
            return isTrue;
        }
        else if(editpass.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Enter valid password", Toast.LENGTH_LONG).show();
            return isTrue;
        }
        else {
            isTrue = true;
            return isTrue;
        }
    }
    private boolean isValidMobile(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() == 10;
        }
        return false;
    }
    private boolean isValid(String number)
    {
        return android.util.Patterns.PHONE.matcher(number).matches();
    }
}