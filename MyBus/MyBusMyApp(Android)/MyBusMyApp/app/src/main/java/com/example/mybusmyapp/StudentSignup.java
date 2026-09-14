package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class StudentSignup extends AppCompatActivity {

    TextInputEditText fname, address_et, usrname_et, password_et, mobile_et;
    Button btnsignup;
    TextView txtsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        fname = (TextInputEditText) findViewById(R.id.name);
        address_et = (TextInputEditText) findViewById(R.id.address);
        usrname_et = (TextInputEditText) findViewById(R.id.usrname_et);
        password_et = (TextInputEditText) findViewById(R.id.password_et);
        mobile_et = (TextInputEditText) findViewById(R.id.mobile);
        btnsignup = (Button) findViewById(R.id.btnsignup);
        txtsignin=(TextView)findViewById(R.id.txtsignin);


        txtsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),Login.class);
                startActivity(in);
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String name = fname.getText().toString().trim();
        final String address = address_et.getText().toString().trim();
        final String email = usrname_et.getText().toString().trim();
        final String password = password_et.getText().toString().trim();
        final String mobile = mobile_et.getText().toString().trim();


        if (TextUtils.isEmpty(name)) {
            fname.setError("Please enter name");
            fname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            address_et.setError("Please enter address");
            address_et.requestFocus();
            return;
        }
//            if (TextUtils.isEmpty(email)) {
//                email.setError("Please enter your email");
//                email.requestFocus();
//                return;
//            }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            usrname_et.setError("Enter a valid email");
            usrname_et.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            mobile_et.setError("Enter a Mobile No");
            mobile_et.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            password_et.setError("Enter a password");
            password_et.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();

        params.put("username", name);
        params.put("password", password);
        params.put("email", email);
        params.put("mob", mobile);
        params.put("address", address);

        //returing the response
        String id=Network.connect("http://"+Network.IP+"/registration.php",params);
        System.out.println("resp"+id);
        if(id.trim()=="0"){
            Toast.makeText(getApplicationContext(),"Wrong Info",Toast.LENGTH_LONG).show();

        }else {
          Intent in=new Intent(getApplicationContext(),Login.class);
          startActivity(in);
        }
//        class RegisterUser extends AsyncTask<Void, Void, String> {
//
//       private ProgressBar progressBar;
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                //creating request handler object
////                RequestHandler requestHandler = new RequestHandler();
//
//                //creating request parameters
//                HashMap<String, String> params = new HashMap<>();
//
//                params.put("username", name);
//                params.put("password", password);
//                params.put("email", email);
//                params.put("mob", mobile);
//                params.put("address", address);
//
//                //returing the response
//String id=Network.connect("http://"+Network.IP,params);
//                System.out.println("resp"+id);
////                System.out.println("url" + URLs.URL_REGISTER);
//                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
//            }
////            protected void onPreExecute() {
////                super.onPreExecute();
////                //displaying the progress bar while user registers on the server
////                progressBar = (ProgressBar) findViewById(R.id.progressBar);
////                progressBar.setVisibility(View.VISIBLE);
////            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                //hiding the progressbar after completion
//                progressBar.setVisibility(View.GONE);
//                System.out.println("sdddddd"+s);
//                try {
//
//                    startActivity(new Intent(getApplicationContext(), Login.class));
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        //executing the async task
//        RegisterUser ru = new RegisterUser();
//        ru.execute();





}
    }
